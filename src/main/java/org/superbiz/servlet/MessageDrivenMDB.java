/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.superbiz.servlet;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nb
 */
@MessageDriven(mappedName = "jms/NewMessage", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    })
public class MessageDrivenMDB implements MessageListener {

    @Resource
    private MessageDrivenContext mdc;
    @PersistenceContext(unitName="")
    private EntityManager em;

    public MessageDrivenMDB() {
    }

    public void save(Object object) {
        em.persist(object);
    }

    public void onMessage(Message message) {
        ObjectMessage msg = null;
     try {
          if (message instanceof ObjectMessage) {
          msg = (ObjectMessage) message;
              JpaBean e = (JpaBean) msg.getObject();
              save(e);
          }
     } catch (JMSException e) {
          e.printStackTrace();
          mdc.setRollbackOnly();
     } catch (Throwable te) {
          te.printStackTrace();
     }

    }
    
}
