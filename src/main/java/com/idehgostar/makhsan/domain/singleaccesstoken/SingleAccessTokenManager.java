package com.idehgostar.makhsan.domain.singleaccesstoken;

import com.idehgostar.makhsan.domain.user.User;
import core.service.GenericManager;

/**
 * Created by hassan on 03/11/2015.
 */
public interface SingleAccessTokenManager extends GenericManager<SingleAccessToken,Long> {
    /**
     * tries to use a token
     * @param token token string
     * @return token
     * @throws InvalidTokenException if token is invalid or exceeds use time
     */
    SingleAccessToken useToken(String token) throws InvalidTokenException;

    /**
     * creates a singleAccessToken with given parameters
     * @param user
     * @param type
     * @param validMinutes
     * @param allowedUsedTimes
     * @param data
     * @return
     */
    SingleAccessToken generateAccessToken(User user, SingleAccessToken.Type type, Integer validMinutes, Integer allowedUsedTimes, String data);
}
