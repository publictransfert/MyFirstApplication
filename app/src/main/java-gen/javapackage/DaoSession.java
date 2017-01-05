package javapackage;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import javapackage.Eleve;

import javapackage.EleveDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig eleveDaoConfig;

    private final EleveDao eleveDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        eleveDaoConfig = daoConfigMap.get(EleveDao.class).clone();
        eleveDaoConfig.initIdentityScope(type);

        eleveDao = new EleveDao(eleveDaoConfig, this);

        registerDao(Eleve.class, eleveDao);
    }
    
    public void clear() {
        eleveDaoConfig.getIdentityScope().clear();
    }

    public EleveDao getEleveDao() {
        return eleveDao;
    }

}
