package com.idehgostar.makhsan.domain.singleaccesstoken;

import core.dao.GenericDao;

/**
 * Created by hassan on 03/11/2015.
 */
public interface SingleAccessTokenDao extends GenericDao<SingleAccessToken, Long> {
    /**
     * loads a token by its token string
     * @param token
     * @return
     */
    SingleAccessToken loadAccessTokenByToken(String token);
}
