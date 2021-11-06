package com.bookstore.dao_impl;

import com.bookstore.entity.CuonSachEntity;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NavigationDao_impl<E> implements com.bookstore.dao.NavigationDao {
    @Autowired
    SessionFactory factory;


    private int totalRecords;
    private int currentPage;
    private List<E> list;
    private int maxResult;
    private int totalPages;

    private int maxNavigationPage;


    // @page: 1, 2, ..

    StringBuilder sql1;
    Query<CuonSachEntity> query;

    private List<Integer> navigationPages;


    public void Navigation( int page, int maxResult, int maxNavigationPage)
    {
        Session session = factory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        try{
////            StringBuilder hql = new StringBuilder("from ");
////            hql.append(this.getPersistenceClassName());
            //String getTable = this.E.getName();
            sql1 = new StringBuilder("select e from "+  CuonSachEntity.class.getName() +" e");
            query = session.createQuery(sql1.toString(),CuonSachEntity.class);

        }
        catch (HibernateException e){
            transaction.rollback();
            throw e;
        }

        final int pageIndex = page - 1 < 0 ? 0 : page - 1;

        int fromRecordIndex = pageIndex * maxResult;
        int maxRecordIndex = fromRecordIndex + maxResult;

        ScrollableResults resultScroll = query.scroll(ScrollMode.SCROLL_INSENSITIVE  );

        List<E> results = new ArrayList<E>();

        boolean hasResult =   resultScroll.first();

        if (hasResult) {

            // Scroll to position:
            hasResult = resultScroll.scroll(fromRecordIndex);

            if (hasResult) {
                do {
                    E record = (E) resultScroll.get(0);
                    results.add(record);
                } while (resultScroll.next()//
                        && resultScroll.getRowNumber() >= fromRecordIndex
                        && resultScroll.getRowNumber() < maxRecordIndex);

            }

            // Go to Last record.
            resultScroll.last();
        }

        // Total Records
        this.totalRecords = resultScroll.getRowNumber() + 1;
        this.currentPage = pageIndex + 1;
        this.list = results;
        this.maxResult = maxResult;

        if (this.totalRecords % this.maxResult == 0) {
            this.totalPages = this.totalRecords / this.maxResult;
        } else {
            this.totalPages = (this.totalRecords / this.maxResult) + 1;
        }

        this.maxNavigationPage = maxNavigationPage;
        resultScroll.close();

        this.calcNavigationPages();
    }

    public void calcNavigationPages() {

        this.navigationPages = new ArrayList<Integer>();

        int current = this.currentPage > this.totalPages ? this.totalPages : this.currentPage;

        int begin = current - maxNavigationPage/2;
        int end = current + maxNavigationPage/2;

        if( maxNavigationPage%2 == 0)
            end =end - 1;

        if(end >totalPages) {
            end = totalPages;
            begin = end -(maxNavigationPage-1);
            if(begin < 1)
                begin =1;

        }
        if(begin < 1) {
            begin =1;
            end = begin + (maxNavigationPage-1);
            if( end > totalPages)
                end =totalPages;

        }

        // The first page
        /*navigationPages.add(1);
        if (begin > 2) {


            // Using for '...'
            navigationPages.add(-1);
        }*/

        for (int i = begin; i <= end; i++) {
            if (i > 0 && i <= this.totalPages) {
                navigationPages.add(i);
            }
        }

       /* if (end < this.totalPages - 2) {

            // Using for '...'
            navigationPages.add(-1);
        }*/

        // The last page.
        /*navigationPages.add(this.totalPages);*/
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public List<E> getList() {
        return list;
    }

    public int getMaxResult() {
        return maxResult;
    }

    public List<Integer> getNavigationPages() {
        return navigationPages;
    }
}
