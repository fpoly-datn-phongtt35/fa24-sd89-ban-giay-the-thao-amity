package org.example.backend.constants;

import java.time.Instant;

public class Constant {
    public static final String PHOTO_DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";

    public static final String X_REQUESTED_WITH = "X-Requested-With";

    public static final String API_CLIENT = "/";

    public static final Instant CURRENT_TIME = Instant.now();

}
