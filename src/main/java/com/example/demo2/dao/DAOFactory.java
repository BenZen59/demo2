package com.example.demo2.dao;

public class DAOFactory {
    public static CouleurDAO getCouleurDAO() {
        return new CouleurDAO();
    }

    public static PaysDAO getPaysDAO() {
        return new PaysDAO();
    }
}