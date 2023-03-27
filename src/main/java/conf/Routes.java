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

package conf;

import controllers.BidderController;
import controllers.ProductController;
import filters.CorsHandler;
import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;
import ninja.utils.NinjaProperties;
import controllers.EmpController;

import com.google.inject.Inject;

//import controllers.ApiController;
//import controllers.ApplicationController;
//import controllers.ArticleController;
//import controllers.LoginLogoutController;

public class Routes implements ApplicationRoutes {
    
    @Inject
    NinjaProperties ninjaProperties;



    /**
     * Using a (almost) nice DSL we can configure the router.
     * 
     * The second argument NinjaModuleDemoRouter contains all routes of a
     * submodule. By simply injecting it we activate the routes.
     * 
     * @param router
     *            The default router of this application
     */
    @Override
    public void init(Router router) {  
        
        // puts test data into db:
//        if (!ninjaProperties.isProd()) {
//            router.GET().route("/setup").with(ApplicationController::setup);
//        }
        
        ///////////////////////////////////////////////////////////////////////
        // Login / Logout
        ///////////////////////////////////////////////////////////////////////
//        router.GET().route("/login").with(LoginLogoutController::login);
//        router.POST().route("/login").with(LoginLogoutController::loginPost);
//        router.GET().route("/logout").with(LoginLogoutController::logout);
        
        ///////////////////////////////////////////////////////////////////////
        // Create new article
        ///////////////////////////////////////////////////////////////////////
//        router.GET().route("/article/new").with(ArticleController::articleNew);
//        router.POST().route("/article/new").with(ArticleController::articleNewPost);






        // ADD USER

        router.POST().route("/employee/add").filters(CorsHandler.class).with(EmpController::CreateUser);

        // ADD PRODUCT

        router.POST().route("/product/add").filters(CorsHandler.class).with(ProductController::CreateProduct);

        router.GET().route("/notInAuctionProducts").with(ProductController::allNotInAuctionProducts);

        router.GET().route("/product/count").with(ProductController::count);

        // router.GET().route("product/delete").with(EmpController::deleteProduct);

        router.GET().route("/employeeCount").with(EmpController::countAllUsers);


        router.POST().route("/bidder/add").with(BidderController::addBidder);

        router.POST().route("/bidder/count").with(BidderController::countTotalBids);

        router.POST().route("/totalWins").with(ProductController::totalWin);

        router.POST().route("/totalLoss").with(ProductController::totalLoss);

        router.GET().route("/inAuctionProducts").filters(CorsHandler.class).with(ProductController::allInAuctionProducts);

        router.GET().route("/isActiveProducts").filters(CorsHandler.class).with(ProductController::isActiveProducts);

        router.POST().route("/product/{id}").with(ProductController::singleProductWithId);

        router.POST().route("/bidders/{productId}").filters(CorsHandler.class).with(BidderController::findBidder);

        router.GET().route("/getUser").filters(CorsHandler.class).with(EmpController::getUser);

        router.POST().route("/employee/get/{email}").filters(CorsHandler.class).with(EmpController::getUserEmail);


        router.POST().route("/updateIsRejected/{productId}").filters(CorsHandler.class).with(ProductController::updateIsRejected);


        router.POST().route("/updateInAuction/{productId}").filters(CorsHandler.class).with(ProductController::updateInAuction);



        router.GET().route("/product/winner").with(BidderController::findBidderWithMaxBid);

        router.POST().route("/updateCurrentBid").with(BidderController::updateCurrentBid);

        router.GET().route("/employee/validate/{email}/{password}").filters(CorsHandler.class).with(EmpController::validateUser);


        router.GET().route("/logout").filters(CorsHandler.class).with(EmpController::logout);

        router.GET().route("/getProducts/{owner}").filters(CorsHandler.class).with(ProductController::getAllProducts);



































//        router.GET().route("/employee/get").with(EmpController::getEmployee);
//        router.GET().route("/employee/getAll").with(EmpController::getAllEmployee);
        
        ///////////////////////////////////////////////////////////////////////
        // Create new article
        ///////////////////////////////////////////////////////////////////////
//        router.GET().route("/article/{id}").with(ArticleController::articleShow);

        ///////////////////////////////////////////////////////////////////////
        // Api for management of software
        ///////////////////////////////////////////////////////////////////////
//        router.GET().route("/api/{username}/articles.json").with(ApiController::getArticlesJson);
//        router.GET().route("/api/{username}/article/{id}.json").with(ApiController::getArticleJson);
//        router.GET().route("/api/{username}/articles.xml").with(ApiController::getArticlesXml);
//        router.POST().route("/api/{username}/article.json").with(ApiController::postArticleJson);
//        router.POST().route("/api/{username}/article.xml").with(ApiController::postArticleXml);

//        router.GET().route("/article/addUser").with(ArticleController::ag);
 
        ///////////////////////////////////////////////////////////////////////
        // Assets (pictures / javascript)
        ///////////////////////////////////////////////////////////////////////    
        router.GET().route("/assets/webjars/{fileName: .*}").with(AssetsController::serveWebJars);
        router.GET().route("/assets/{fileName: .*}").with(AssetsController::serveStatic);
        
        ///////////////////////////////////////////////////////////////////////
        // Index / Catchall shows index page
        ///////////////////////////////////////////////////////////////////////
       // router.GET().route("/.*").with(ApplicationController::index);


        router.OPTIONS().route("/.*").with(EmpController::indexOptions);

    }

}
