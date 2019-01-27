package org.firstinspires.ftc.teamcode;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.Response;

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
        return Response.newFixedLengthResponse("Coming soon...");
    }
}
