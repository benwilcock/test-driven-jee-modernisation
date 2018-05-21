package org.superbiz.servlet;


import javax.ejb.EJBAccessException;
import javax.ejb.EJBException;
import javax.ejb.Stateful;
import java.util.logging.Logger;

@Stateful
public class StatefulEJB {

    public void testEjbExceptionMethod(){
        throw new EJBException("blah");
    }

    public void testEjbAccessExceptionMethod(){
        throw new EJBAccessException("blah");
    }

    public void testGenericExceptionMethod(){
        throw new RuntimeException("blah");
    }

    private static final String TAG = "StatefulEJB";
    private static final Logger LOGGER = Logger.getLogger(TAG);
}
