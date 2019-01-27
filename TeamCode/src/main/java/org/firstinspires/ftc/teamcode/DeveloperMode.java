package org.firstinspires.ftc.teamcode;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by dcrenshaw on 1/20/19.
 *
 * Developer Mode is a small HTTP server that provides relevant information on all robot hardware.
 *
 */

public class DeveloperMode  extends NanoHTTPD {
    public DeveloperMode() {
        super(8080);
    }
    public Response serve(IHTTPSession session) {
        return NanoHTTPD.newFixedLengthResponse("Coming soon...");
    }
}
