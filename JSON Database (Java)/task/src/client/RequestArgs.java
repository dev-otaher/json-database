package client;

import com.beust.jcommander.Parameter;

class RequestArgs {
    @Parameter(names = {"-t"})
    private String type;

    @Parameter(names = {"-k"})
    private String key;

    @Parameter(names = {"-v"})
    private String value;
}
