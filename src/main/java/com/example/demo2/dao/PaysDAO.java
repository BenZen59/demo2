package com.example.demo2.dao;

import metier.Couleur;
import metier.Pays;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static com.example.demo2.dao.ConnectDtb.connexion;

public class PaysDAO extends DAO<Pays, Pays, Integer> {
    @Override
    public Pays getByID(Integer id) {
        String sqlRequest = "SELECT id_pays ,nom_pays from pays where id_pays = " + id;
        Couleur couleur;
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sqlRequest);
            if (rs.next()) return new Pays(rs.getInt(1), rs.getString(2));
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public ArrayList<Pays> getAll() {
        ArrayList<Pays> liste = new ArrayList<>();
        String sqlRequest = "SELECT ID_PAYS, NOM_PAYS FROM PAYS";
        try (Statement statement = connexion.createStatement()) {
            ResultSet rs = statement.executeQuery(sqlRequest);
            while (rs.next()) {
                liste.add(new Pays(rs.getInt(1), rs.getString(2)));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public ArrayList<Pays> getLike(Pays object) {
        String sqlCommand = "SELECT ID_PAYS,NOM_PAYS from COULEUR where ID_PAYS like '%" + object.getNomPays() + "%'";
        ArrayList<Pays> liste = new ArrayList<>();
        try (Statement statement = connexion.createStatement()) {
            ResultSet rs = statement.executeQuery(sqlCommand);
            while (rs.next()) {
                liste.add(new Pays(rs.getInt(1), rs.getString(2)));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }



    @Override
    public boolean insert(Pays object) {
        return false;
    }

    @Override
    public boolean update(Pays object) {
        return false;
    }

    @Override
    public boolean delete(Pays object) {
        return false;
    }
}
