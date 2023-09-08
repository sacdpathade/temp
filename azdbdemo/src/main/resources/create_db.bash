#! /bin/bash
echo "Create DB"
AZ_SUBSCRIPTION="HC-ACI-Titan-NonProd"
AZ_RESOURCE_GROUP="hc-ant-dev-temp"
AZ_DATABASE_NAME="antserviceapi-db"
AZ_LOCATION="eastus2"
AZ_SQL_SERVER_USERNAME="demo"
AZ_SQL_SERVER_PASSWORD="AntService123#"
AZ_LOCAL_IP_ADDRESS="49.36.49.56"
echo $AZ_LOCATION
echo IP Address $AZ_LOCAL_IP_ADDRESS
az sql server create \
    --resource-group $AZ_RESOURCE_GROUP \
    --name $AZ_DATABASE_NAME \
    --location $AZ_LOCATION \
    --admin-user $AZ_SQL_SERVER_USERNAME \
    --admin-password $AZ_SQL_SERVER_PASSWORD \
    | jq
az sql db create \
    --resource-group $AZ_RESOURCE_GROUP \
    --name demo \
    --server $AZ_DATABASE_NAME \
    | jq