package atl.repo;

import atl.model.Proba;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class RepositoryBDProbe implements RepositoryProbe{

    private final static Logger logger = LogManager.getLogger(RepositoryBDProbe.class);
    private JDBCUtils dbUtils;

    public RepositoryBDProbe(Properties props) {
        this.dbUtils = new JDBCUtils(props);
    }

    public RepositoryBDProbe(){
        Properties serverProps = new Properties();
        try{
            serverProps.load(RepositoryBDProbe.class.getResourceAsStream("/server.properties"));
            System.out.println("Server props set");
            serverProps.list(System.out);
        } catch (IOException e) {
            System.err.println("Cannot find props file!" + e);
            return;
        }
        this.dbUtils = new JDBCUtils(serverProps);
    }

    @Override
    public Proba findOne(Long aLong) {
        logger.traceEntry("Params: {}", aLong);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement statement = con.prepareStatement("SELECT * FROM \"Proba\" where id = ?")) {
            statement.setLong(1, aLong);
            try(ResultSet set = statement.executeQuery()){
                if(set.next()){
                    String desc = set.getString("descriere");
                    int varstaMin = set.getInt("varsta_min");
                    int varstaMax = set.getInt("varsta_max");
                    Proba proba = new Proba(desc, varstaMin, varstaMax);
                    proba.setId(aLong);
                    return logger.traceExit(proba);
                }
            }
        } catch (SQLException throwables) {
            logger.error(throwables);
            System.err.println(throwables);
        }
        logger.traceExit("No proba found with id {}", aLong);
        return null;
    }

    @Override
    public Iterable<Proba> findAll() {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Proba> probe = new ArrayList<>();
        try(PreparedStatement statement = con.prepareStatement("SELECT * FROM \"Proba\"")) {
            try(ResultSet set = statement.executeQuery()){
                while(set.next()) {
                    Long id = set.getLong("id");
                    String desc = set.getString("descriere");
                    int varstaMin = set.getInt("varsta_min");
                    int varstaMax = set.getInt("varsta_max");
                    Proba proba = new Proba(desc, varstaMin, varstaMax);
                    proba.setId(id);
                    probe.add(proba);
                }
            }
        } catch (SQLException throwables) {
            logger.error(throwables);
            System.err.println(throwables);
        }
        return logger.traceExit(probe);
    }

    @Override
    public void save(Proba entity) {
        logger.traceEntry("Params: {}", entity);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement statement = con.prepareStatement("INSERT INTO \"Proba\"(descriere, varsta_min, varsta_max) VALUES (?, ?, ?)")) {
            statement.setString(1, entity.getDescriere());
            statement.setInt(2, entity.getVarstaMinima());
            statement.setInt(3, entity.getVarstaMaxima());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            logger.error(throwables);
            System.err.println(throwables);
        }
        logger.traceExit();
    }

    @Override
    public Proba findProbaByDescriere(String descriere) {
        logger.traceEntry("Params: {}", descriere);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement statement = con.prepareStatement("select * from \"Proba\" where descriere = ?")) {
            statement.setString(1, descriere);
            try(ResultSet set = statement.executeQuery()){
                if(set.next()){
                    Long id = set.getLong("id");
                    int varstaMin = set.getInt("varsta_min");
                    int varstaMax = set.getInt("varsta_max");
                    Proba proba = new Proba(descriere, varstaMin, varstaMax);
                    proba.setId(id);
                    return logger.traceExit(proba);
                }
            }
        } catch (SQLException throwables) {
            logger.error(throwables);
            System.err.println(throwables);
        }
        logger.traceExit("No proba found with descriere {}", descriere);
        return null;
    }

    @Override
    public int updateProba(long id, Proba proba) {
        Connection con = dbUtils.getConnection();
        Proba proba1 = findOne(id);
        if(proba1 != null){
            try(PreparedStatement statement = con.prepareStatement("UPDATE \"Proba\" set descriere = ? where id = ?")){
                statement.setString(1, proba.getDescriere());
                statement.setLong(2, id);
                try(ResultSet set = statement.executeQuery()){
                    System.out.println("modified!");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return 1;
        }
        else{
            return 0;
        }
    }

    @Override
    public int deleteProba(long id) {
        Connection con = dbUtils.getConnection();
        Proba proba = findOne(id);
        if(proba != null){
            try(PreparedStatement statement = con.prepareStatement("DELETE FROM \"Proba\" where id = ?")){
                statement.setLong(1, id);
                try(ResultSet set = statement.executeQuery()){
                    System.out.println("modified!");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return 1;
        }
        else{
            return 0;
        }
    }
}
