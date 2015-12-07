package com.idehgostar.makhsan.domain.singleaccesstoken;

import core.dao.GenericDaoImpl;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by hassan on 03/11/2015.
 */
public class SingleAccessTokenDaoImpl extends GenericDaoImpl<SingleAccessToken,Long> implements SingleAccessTokenDao {
    public SingleAccessTokenDaoImpl() {
        super(SingleAccessToken.class);
    }

    @Override
    public SingleAccessToken loadAccessTokenByToken(String token){
        Query query = entityManager.createNamedQuery("loadValidAccessToken");
        query.setParameter("token",token);
        query.setParameter("expDate", new Date(), TemporalType.TIMESTAMP);
        try {
            SingleAccessToken singleAccessToken = (SingleAccessToken) query.getSingleResult();
            return singleAccessToken;
        }catch (NoResultException e){
            return null;
        }
    }
}
