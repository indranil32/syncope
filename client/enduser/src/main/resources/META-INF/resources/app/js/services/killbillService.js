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

'use strict';

angular.module('SyncopeEnduserApp')
        .factory('KillBillService', ['$q', '$http',
          function ($q, $http) {

            var killbillService = {};

            var error = function (response) {
                console.error("Something went wrong", response);
                return $q.reject(response.data || response.statusText);
            };
            
            /*
            [
			  {
			    "product": "Sports",
			    "plan": "sports-monthly",
			    "priceList": "DEFAULT",
			    "finalPhaseBillingPeriod": "MONTHLY",
			    "finalPhaseRecurringPrice": [
			      {
			        "currency": "GBP",
			        "value": 375
			      },
			      {
			        "currency": "USD",
			        "value": 500
			      }
			    ]
			  },
			  {
			    "product": "Standard",
			    "plan": "standard-monthly",
			    "priceList": "DEFAULT",
			    "finalPhaseBillingPeriod": "MONTHLY",
			    "finalPhaseRecurringPrice": [
			      {
			        "currency": "GBP",
			        "value": 75
			      },
			      {
			        "currency": "USD",
			        "value": 100
			      }
			    ]
			  }
			]
            */
            killbillService.getBaseAvailablePackages = function (req) {
              return  $http(req)
                      .then(function (response) {
                        return response.data;
                      }, error);
            };
            
            /*
             [
			  {
			    "paymentMethodId": "916619a4-02bb-4d3d-b3da-2584ac897b19",
			    "externalKey": "coolPaymentMethod",
			    "accountId": "84c7e0d4-a5ed-405f-a655-3ed16ae19997",
			    "isDefault": false,
			    "pluginName": "__EXTERNAL_PAYMENT__",
			    "pluginInfo": null,
			    "auditLogs": []
			  },
			  {
			    "paymentMethodId": "dc89832d-18a3-42fd-b3be-cac074fddb36",
			    "externalKey": "paypal",
			    "accountId": "ca15adc4-1061-4e54-a9a0-15e773b3b154",
			    "isDefault": false,
			    "pluginName": "killbill-paypal-express",
			    "pluginInfo": null,
			    "auditLogs": []
			  }
			 ]
             */            
            killbillService.getPaymentMethods = function (req) {
                return  $http(req)
                        .then(function (response) {
                          return response.data;
                        }, error);
            };
            
            killbillService.getKillBillDetails = function () {
            	return $http.
		                get('../api/dynamicTemplate').
		                then(function (response) {
		                  return response.data["customProperties"];
		                }, error);
            };
            
            return killbillService;         
}]);


