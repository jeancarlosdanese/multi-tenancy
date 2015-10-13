/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
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
package br.com.danese.multitenancy.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.danese.multitenancy.dao.BaseDAO;
import br.com.danese.multitenancy.model.Member;

@ApplicationScoped
public class MemberRepository extends BaseDAO {

	private static final long serialVersionUID = -5820252843468620215L;

	public Member findById(Long id) {
		return (Member) session().get(Member.class, id);
	}

	public Member findByEmail(String email) {
		Criteria criteria = session().createCriteria(Member.class);
		criteria.add(Restrictions.eq("email", email));

		return (Member) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Member> findAllOrderedByName() {
		Criteria criteria = session().createCriteria(Member.class);
		criteria.addOrder(Order.asc("name"));

		return criteria.list();
	}

}
