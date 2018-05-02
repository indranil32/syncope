/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.syncope.core.persistence.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.apache.syncope.core.persistence.api.entity.OIDCProvider;
import org.apache.syncope.core.persistence.api.entity.OIDCUserTemplate;
import org.apache.syncope.core.persistence.jpa.entity.resource.AbstractAnyTemplate;

@Entity
@Table(name = JPAOIDCUserTemplate.TABLE, uniqueConstraints =
        @UniqueConstraint(columnNames = { "op_id" }))
public class JPAOIDCUserTemplate extends AbstractAnyTemplate implements OIDCUserTemplate {

    public static final String TABLE = "OIDCUserTemplate";

    private static final long serialVersionUID = 3964321047520954968L;

    @ManyToOne
    private JPAOIDCProvider op;

    @Override
    public OIDCProvider getOP() {
        return op;
    }

    @Override
    public void setOP(final OIDCProvider op) {
        checkType(op, JPAOIDCProvider.class);
        this.op = (JPAOIDCProvider) op;
    }

}