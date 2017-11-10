/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hifly.hibernate.batch.service;


import org.hibernate.Session;
import org.hifly.hibernate.batch.model.Member;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Stateless
public class MemberRegistration {


    @PersistenceContext
    private EntityManager em;


    public void register(Member member) throws Exception {
        Session session = (Session) em.getDelegate();
        session.persist(member);
    }

    public void bulkInsert(List<Member> memberRegistrations) {
        int batchSize = 25;

        //FIXME Hibernate 5.2
        //em.unwrap( Session.class ).setJdbcBatchSize( batchSize );


        for ( int i = 0; i < memberRegistrations.size(); ++i ) {
            em.persist( memberRegistrations.get(i) );

            if ( i > 0 && i % batchSize == 0 ) {
                //flush a batch of inserts and release memory
                em.flush();
                em.clear();
            }
        }



    }
}
