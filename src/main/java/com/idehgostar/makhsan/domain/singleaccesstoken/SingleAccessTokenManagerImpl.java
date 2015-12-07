package com.idehgostar.makhsan.domain.singleaccesstoken;

import com.idehgostar.makhsan.domain.user.User;
import core.dao.GenericDao;
import core.service.GenericManagerImpl;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hasan on 9/12/14.
 */
public class SingleAccessTokenManagerImpl extends GenericManagerImpl<SingleAccessToken,Long> implements SingleAccessTokenManager{

    @Autowired
    private SingleAccessTokenDao singleAccessTokenDao;

    public SingleAccessTokenManagerImpl(GenericDao<SingleAccessToken, Long> genericDao) {
        super(genericDao);
    }

    @Override
    public SingleAccessToken useToken(String token) throws InvalidTokenException {
        SingleAccessToken singleAccessToken = singleAccessTokenDao.loadAccessTokenByToken(token);
        if(singleAccessToken == null || !singleAccessToken.use())
            throw new InvalidTokenException("token is not valid");
        singleAccessTokenDao.save(singleAccessToken);
        return singleAccessToken;
    }
    @Override
    public SingleAccessToken generateAccessToken(User user, SingleAccessToken.Type type, Integer validMinutes, Integer allowedUsedTimes, String data){
        String token = RandomStringUtils.random(60, true, true);

        SingleAccessToken sat = new SingleAccessToken(user,token,type,validMinutes,allowedUsedTimes,data);
        sat= singleAccessTokenDao.save(sat);
        return sat;
    }
}
