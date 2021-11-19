package com.bookstore.dao;

public interface NavigationDao {
     void Navigation(int page, int maxResult, int maxNavigationPage);
     void calcNavigationPages();
}
