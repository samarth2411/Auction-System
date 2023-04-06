/**
 * Copyright (C) the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/**
 * Copyright (C) 2012-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.google.inject.persist.Transactional;

import com.google.inject.Inject;
import com.google.inject.Provider;
import dto.UserDto;
import models.AppUser;
import models.Bidder;
import models.Product;

import java.util.List;


public class EmployeeDao {
    
    @Inject
    Provider<EntityManager> entityManagerProvider;
    


    /**
     * Get single result without throwing NoResultException, you can of course just catch the
     * exception and return null, it's up to you.
     */
    @Transactional
    public AppUser AddUser(UserDto userDto){
            EntityManager em = entityManagerProvider.get();
            AppUser appUser = new AppUser();
            appUser.setName(userDto.getName());
            appUser.setPassword(userDto.getPassword());
            appUser.setEmail(userDto.getEmail());
            em.persist(appUser);
            System.out.println("User Created Successfully");
            return appUser;
    }

    @Transactional
    public Long countAllUsers() throws Exception{
        try {
            EntityManager em = entityManagerProvider.get();
            long count = em.createNamedQuery("AppUser.getAllUsers",Long.class).getSingleResult();
            return count;
        }
        catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
    @Transactional
    public AppUser validate(String email,String password){
        try{
            EntityManager em = entityManagerProvider.get();
            System.out.println(email);
            System.out.println(password);
            TypedQuery<AppUser> q = em.createQuery("SELECT u from AppUser u where u.email=:email and u.password=:password", AppUser.class);
            List<AppUser> appUser = q.setParameter("email",email).setParameter("password",password).getResultList();
            if(appUser.size()==0){
                return null;
            }
            return appUser.get(0);
        }
        catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
    @Transactional
    public AppUser getUser(String email) throws Exception{
        try {
            EntityManager em = entityManagerProvider.get();
            Query namedQuery = em.createNamedQuery("AppUser.getUserByMail");
            List<AppUser> list = namedQuery.setParameter("email", email).getResultList();
            if (list.size() == 0) {
                return null;
            } else {
                return list.get(0);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            throw e;
        }

    }
    @Transactional
    public AppUser getUserEmail(String email){
        EntityManager em = entityManagerProvider.get();
        Query namedQuery = em.createNamedQuery("AppUser.getUserByMail");
        List<AppUser> list = namedQuery.setParameter("email", email).getResultList();
        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }




//    public void deleteProduct(Long id){
//        EntityManager em = entityManagerProvider.get();
//        Query namedQuery = em.createNamedQuery("ProductEntity.DeleteByProductId");
//        namedQuery.setParameter("id",id).executeUpdate();
//
//    }

//    @Transactional
//    public User getEmployeeByNameWithNamedQuery(String name) {
//        EntityManager em = entityManagerProvider.get();
//        Query namedQuery = em.createNamedQuery("Employee.findByEmployeeName");
//        namedQuery.setParameter("employeeName", name);
//        return  (User)namedQuery.getSingleResult();
//    }

//    public List<User> getEmployee() {
//        EntityManager em = entityManagerProvider.get();
//        Query namedQuery = em.createNamedQuery("Employee.findAllEmployee");
//        return  namedQuery.getResultList();
//    }

//    public Employee deleteEmployee(String name){
//        EntityManager em = entityManagerProvider.get();
//
//
//    }

}
