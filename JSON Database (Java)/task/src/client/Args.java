package client;

import com.beust.jcommander.Parameter;

public class Args {
    @Parameter(names = {"-t"})
    String requestType;

    @Parameter(names = {"-i"})
    int index;

    @Parameter(names = {"-m"})
    String data;
}
