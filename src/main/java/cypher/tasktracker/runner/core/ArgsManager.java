package cypher.tasktracker.runner.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArgsManager {
    private List<String> args = new ArrayList<String>();

    public ArgsManager(final String[] args) {
        this.setArgs(Arrays.stream(args).toList());
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }

    public List<String> getArgs() {
        return this.args;
    }

    public boolean hasAdditionalActionKey() {
        return this.args.size() > 1;
    }
}
