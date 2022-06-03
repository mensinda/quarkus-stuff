package app;

import a.api.AApi;
import b.api.BApi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class App {

    @Inject
    AApi aApi;

    @Inject
    BApi bApi;

    public String doStuff() {
        return aApi.oracle() + " -- " + bApi.someStrGenerator();
    }

}
