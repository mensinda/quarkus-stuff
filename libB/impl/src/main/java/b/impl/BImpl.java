package b.impl;

import a.api.AApi;
import b.api.BApi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class BImpl implements BApi {

    @Inject
    AApi aApi;


    @Override
    public String someStrGenerator() {
        return "oracle=" + aApi.oracle();
    }
}
