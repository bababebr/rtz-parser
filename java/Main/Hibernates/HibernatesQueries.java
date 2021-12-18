package Main.Hibernates;

import Main.Hibernates.Entities.RouteEntity;
import Main.Route.Route;
import Main.RTZ.RouteFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.io.File;
import java.util.List;

public class HibernatesQueries {

    public static void checkStatus(){
        Transaction transaction = null;
        try(Session session = HibernatesUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            System.out.println(transaction.getStatus());
            transaction.commit();
            session.close();
        }
    }

    public static Route fetchRoute(int id){
        Transaction transaction;
        try(Session session = HibernatesUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            RouteEntity r = session.get(RouteEntity.class, id);

            transaction.commit();
            session.close();
            return r.toRoute();
        }
    }
    public static Route fetchRoute(String routeName){
        Transaction transaction;
        try(Session session = HibernatesUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query q = session.createSQLQuery("select * from Routes where routename='Fujairah-Basrah'");
            System.out.println(q.getSingleResult().toString());
            transaction.commit();
            session.close();
            return null;
        }
    }

    public static void insertRoute(File routesFolder){
        for(File f : routesFolder.listFiles()){
            if(f.getName().endsWith(".rtz") ){
                try {
                    insertRoute(RouteFactory.factory(f.getAbsolutePath()));
                }
                catch (Exception e){
                    e.printStackTrace();
                    System.out.println("Cannon import route " + f.getName());
                }
            }
        }
    }

    public static void insertRoute(Route r) {
        Transaction transaction = null;
        if(r == null){
            return;
        }
        try(Session session = HibernatesUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String querry = String.format("SELECT EXISTS (SELECT 1 FROM routes WHERE routename='%s')",r.getRouteName());
            Boolean b = (Boolean) session.createSQLQuery(querry).getSingleResult();

            if(!b){
                session.save(new RouteEntity(r));
                System.out.println(String.format("Route %s inserted to DB",r.getRouteName()));
            }
            else {
                System.out.println(String.format("Route %s exist in DB",r.getRouteName()));
            }

            transaction.commit();
            session.close();
        }
    }

    public static void deleteRoute(int idRoute) {
        Transaction transaction = null;

        try(Session session = HibernatesUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            int result = session.createSQLQuery(String.format("DELETE FROM routes WHERE id=%d",idRoute)).executeUpdate();
            if(result == 1) {
                System.out.println(String.format("Route Deleted"));
            }
            else {
                System.out.println(String.format("Route with id:%d isn't found in DB", idRoute));
            }
            transaction.commit();
            session.close();
        }
    }

    public static void deleteRoute(String routeName) {
        Transaction transaction = null;

        try(Session session = HibernatesUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String querry = String.format("DELETE FROM Routes where routename='%s'",routeName);
            int result = session.createSQLQuery(querry).executeUpdate();
            if (result == 1) {
                System.out.println(String.format("Route %s has been deleted from DB",routeName));
            }
            else{
                System.out.println(String.format("Route %s doesn't exist in DB",routeName));
            }
            transaction.commit();
            session.close();
        }
    }

    public static void showTable() {
        Transaction transaction;
        try(Session session = HibernatesUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<RouteEntity> s = session.createQuery("from RouteEntity").list();
            System.out.println("ID: \t Name");
            for(RouteEntity r : s){
                System.out.println(r.getId() + "\t\t" + r.getName());
            }
            transaction.commit();
            session.close();
        }
    }

    public static void wipeTable() {
        Transaction transaction;
        try(Session session = HibernatesUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DELETE FROM routes").executeUpdate();
            System.out.println("Table wiped");
            transaction.commit();
            session.close();
        }
    }
}
