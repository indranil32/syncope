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
package org.apache.syncope.core.persistence.jpa.entity.user;

import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.apache.syncope.core.persistence.api.entity.user.User;
import org.apache.syncope.core.persistence.jpa.entity.PGJPAEntityListener;
import org.apache.syncope.core.persistence.jpa.entity.PGPlainAttr;
import org.apache.syncope.core.provisioning.api.serialization.POJOHelper;

public class PGJPAUserListener extends PGJPAEntityListener<User> {

    @Override
    protected List<? extends PGPlainAttr<User>> getValues(final String plainAttrsJSON) {
        return POJOHelper.deserialize(plainAttrsJSON, new TypeReference<List<PGUPlainAttr>>() {
        });
    }

    @PostLoad
    public void read(final PGJPAUser user) {
        super.read(user);
    }

    @PrePersist
    @PreUpdate
    public void save(final PGJPAUser user) {
        super.save(user);
    }
}
