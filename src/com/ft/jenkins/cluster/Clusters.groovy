package com.ft.jenkins.cluster

import com.cloudbees.groovy.cps.NonCPS

import static EnvClusterMapEntry.newEksEntry
import static EnvClusterMapEntry.newEntry
import static com.ft.jenkins.cluster.Environment.*

class Clusters implements Serializable {

  private static final String SLACK_CHANNEL = "#upp-changes"

  @NonCPS
  static Cluster initDeliveryCluster() {
    Cluster deliveryCluster = new Cluster(ClusterType.DELIVERY)
    Environment devEnv = new Environment(DEV_NAME, deliveryCluster)
    devEnv.with {
      slackChannel = SLACK_CHANNEL
      regions = [Region.EU]
      associatedClusterTypes = [ClusterType.DELIVERY, ClusterType.PUBLISHING]
      clusterToApiServerMap = [
              ("${Region.EU}-${ClusterType.DELIVERY}".toString()): newEksEntry(
                      eksClusterName: "eks-delivery-dev-eu",
                      apiServer: "https://5D8C3410C9F0BB07D71A6BED9E0E3E26.gr7.eu-west-1.eks.amazonaws.com",
                      publicEndpoint: "https://upp-k8s-dev-delivery-eu.upp.ft.com"
              )
      ]
      glbMap = [
              (ClusterType.PUBLISHING.toString()): "https://upp-k8s-dev-publish-eu.upp.ft.com",
              (ClusterType.DELIVERY.toString())  : "https://upp-k8s-dev-delivery-eu.upp.ft.com"
      ]
    }

    Environment k8sEnv = new Environment("k8s", deliveryCluster)
    k8sEnv.with {
      slackChannel = SLACK_CHANNEL
      regions = [Region.EU]
      associatedClusterTypes = [ClusterType.DELIVERY, ClusterType.PUBLISHING]
      clusterToApiServerMap = [
              ("${Region.EU}-${ClusterType.DELIVERY}".toString()): newEksEntry(
                      eksClusterName: "eks-delivery-dev-eu",
                      apiServer: "https://5D8C3410C9F0BB07D71A6BED9E0E3E26.gr7.eu-west-1.eks.amazonaws.com",
                      publicEndpoint: "https://upp-k8s-dev-delivery-eu.upp.ft.com"
              )
      ]
      glbMap = [
              (ClusterType.PUBLISHING.toString()): "https://upp-k8s-dev-publish-eu.upp.ft.com",
              (ClusterType.DELIVERY.toString())  : "https://upp-k8s-dev-delivery-eu.upp.ft.com"
      ]
    }

    Environment stagingEnv = new Environment(STAGING_NAME, deliveryCluster)
    stagingEnv.with {
      slackChannel = SLACK_CHANNEL
      regions = [Region.EU, Region.US]
      associatedClusterTypes = [ClusterType.DELIVERY, ClusterType.PUBLISHING]
      clusterToApiServerMap = [
              ("${Region.EU}-${ClusterType.DELIVERY}".toString()): newEksEntry(
                      eksClusterName: "eks-delivery-staging-eu",
                      apiServer: "https://65E6A68BD1914DC37592094EEB77A14B.gr7.eu-west-1.eks.amazonaws.com",
                      publicEndpoint: "https://upp-staging-delivery-eu.upp.ft.com"
              ),
              ("${Region.US}-${ClusterType.DELIVERY}".toString()): newEksEntry(
                      eksClusterName: "eks-delivery-staging-us",
                      apiServer: "https://B54D477610F54CBD56DDA5775EBBC4B2.gr7.us-east-1.eks.amazonaws.com",
                      publicEndpoint: "https://upp-staging-delivery-us.upp.ft.com"
              )
      ]
      glbMap = [
              (ClusterType.PUBLISHING.toString()): "https://upp-staging-publish-glb.upp.ft.com",
              (ClusterType.DELIVERY.toString())  : "https://upp-staging-delivery-glb.upp.ft.com"
      ]
    }

    Environment prodEnv = new Environment(PROD_NAME, deliveryCluster)
    prodEnv.with {
      slackChannel = SLACK_CHANNEL
      regions = [Region.EU, Region.US]
      associatedClusterTypes = [ClusterType.DELIVERY, ClusterType.PUBLISHING]
      clusterToApiServerMap = [
              ("${Region.EU}-${ClusterType.DELIVERY}".toString()): newEksEntry(
                      eksClusterName: "eks-delivery-prod-eu",
                      apiServer: "https://877C36043718E054E3C02F026E18868E.gr7.eu-west-1.eks.amazonaws.com",
                      publicEndpoint: "https://upp-prod-delivery-eu.upp.ft.com"
              ),
              ("${Region.US}-${ClusterType.DELIVERY}".toString()): newEksEntry(
                      eksClusterName: "eks-delivery-prod-us",
                      apiServer: "https://AFBD281EB996368B2541617F68A8FA64.gr7.us-east-1.eks.amazonaws.com",
                      publicEndpoint: "https://upp-prod-delivery-us.upp.ft.com",
              )
      ]
      glbMap = [
              (ClusterType.PUBLISHING.toString()): "https://upp-prod-publish-glb.upp.ft.com",
              (ClusterType.DELIVERY.toString())  : "https://upp-prod-delivery-glb.upp.ft.com"
      ]
    }

    deliveryCluster.environments = [devEnv, k8sEnv, stagingEnv, prodEnv]
    deliveryCluster
  }

  @NonCPS
  static Cluster initPublishingCluster() {
    Cluster publishingCluster = new Cluster(ClusterType.PUBLISHING)
    Environment devEnv = new Environment(DEV_NAME, publishingCluster)
    devEnv.with {
      slackChannel = SLACK_CHANNEL
      regions = [Region.EU]
      associatedClusterTypes = [ClusterType.PUBLISHING, ClusterType.DELIVERY]
      clusterToApiServerMap = [
              ("${Region.EU}-${ClusterType.PUBLISHING}".toString()): newEksEntry(
                      eksClusterName: "eks-publish-dev-eu",
                      apiServer: "https://9A0ED5B022689CF32F83F9694BE77397.gr7.eu-west-1.eks.amazonaws.com",
                      publicEndpoint: "https://upp-k8s-dev-publish-eu.upp.ft.com"
              )
      ]
      glbMap = [
              (ClusterType.PUBLISHING.toString()): "https://upp-k8s-dev-publish-eu.upp.ft.com",
              (ClusterType.DELIVERY.toString())  : "https://upp-k8s-dev-delivery-eu.upp.ft.com"
      ]
    }

    Environment k8sEnv = new Environment("k8s", publishingCluster)
    k8sEnv.with {
      slackChannel = SLACK_CHANNEL
      regions = [Region.EU]
      associatedClusterTypes = [ClusterType.PUBLISHING, ClusterType.DELIVERY]
      clusterToApiServerMap = [
              ("${Region.EU}-${ClusterType.PUBLISHING}".toString()): newEksEntry(
                      eksClusterName: "eks-publish-dev-eu",
                      apiServer: "https://9A0ED5B022689CF32F83F9694BE77397.gr7.eu-west-1.eks.amazonaws.com",
                      publicEndpoint: "https://upp-k8s-dev-publish-eu.upp.ft.com"
              )
      ]
      glbMap = [
              (ClusterType.PUBLISHING.toString()): "https://upp-k8s-dev-publish-eu.upp.ft.com",
              (ClusterType.DELIVERY.toString())  : "https://upp-k8s-dev-delivery-eu.upp.ft.com"
      ]
    }

    Environment stagingEnv = new Environment(STAGING_NAME, publishingCluster)
    stagingEnv.with {
      slackChannel = SLACK_CHANNEL
      regions = [Region.EU, Region.US]
      associatedClusterTypes = [ClusterType.PUBLISHING, ClusterType.DELIVERY]
      clusterToApiServerMap = [
              ("${Region.EU}-${ClusterType.PUBLISHING}".toString()): newEksEntry(
                      eksClusterName: "eks-publish-staging-eu",
                      apiServer: "https://BED6FF5CE2B60B9038FF15C617D0FA8E.gr7.eu-west-1.eks.amazonaws.com",
                      publicEndpoint: "https://upp-staging-publish-eu.upp.ft.com"
              ),
              ("${Region.US}-${ClusterType.PUBLISHING}".toString()): newEksEntry(
                      eksClusterName: "eks-publish-staging-us",
                      apiServer: "https://6EF3F9A33CEE68A33F24E1E764E421BD.gr7.us-east-1.eks.amazonaws.com",
                      publicEndpoint: "https://upp-staging-publish-us.upp.ft.com"
              )
      ]
      glbMap = [
              (ClusterType.PUBLISHING.toString()): "https://upp-staging-publish-glb.upp.ft.com",
              (ClusterType.DELIVERY.toString())  : "https://upp-staging-delivery-glb.upp.ft.com"
      ]
    }

    Environment prodEnv = new Environment(PROD_NAME, publishingCluster)
    prodEnv.with {
      slackChannel = SLACK_CHANNEL
      regions = [Region.EU, Region.US]
      associatedClusterTypes = [ClusterType.PUBLISHING, ClusterType.DELIVERY]
      clusterToApiServerMap = [
              ("${Region.EU}-${ClusterType.PUBLISHING}".toString()): newEksEntry(
                      eksClusterName: "eks-publish-prod-eu",
                      apiServer: "https://267EEADE3646B2E65BEBF3DC4B0EA304.yl4.eu-west-1.eks.amazonaws.com",
                      publicEndpoint: "https://upp-prod-publish-eu.upp.ft.com"
              ),
              ("${Region.US}-${ClusterType.PUBLISHING}".toString()): newEksEntry(
                      eksClusterName: "eks-publish-prod-us",
                      apiServer: "https://BE9FAEBB02EBAE0055387E3D4E3A999A.gr7.us-east-1.eks.amazonaws.com",
                      publicEndpoint: "https://upp-prod-publish-us.upp.ft.com"
              )
      ]
      glbMap = [
              (ClusterType.PUBLISHING.toString()): "https://upp-prod-publish-glb.upp.ft.com",
              (ClusterType.DELIVERY.toString())  : "https://upp-prod-delivery-glb.upp.ft.com"
      ]
    }
    publishingCluster.environments = [devEnv, k8sEnv, stagingEnv, prodEnv]
    publishingCluster
  }

  @NonCPS
  static Cluster initPacCluster() {
    Cluster pacCluster = new Cluster(ClusterType.PAC)
    Environment stagingEnv = new Environment(STAGING_NAME, pacCluster)
    stagingEnv.with {
      slackChannel = SLACK_CHANNEL
      regions = [Region.EU, Region.US]
      associatedClusterTypes = [ClusterType.PAC]
      clusterToApiServerMap = [
              ("${Region.EU}-${ClusterType.PAC}".toString()): newEksEntry(
                      eksClusterName: "eks-pac-staging-eu",
                      apiServer: "https://03314390459662CFF090C582B89DAA27.gr7.eu-west-1.eks.amazonaws.com/",
                      publicEndpoint: "https://pac-staging-eu.upp.ft.com"
              ),
              ("${Region.US}-${ClusterType.PAC}".toString()): newEksEntry(
                      eksClusterName: "eks-pac-staging-us",
                      apiServer: "https://2A91C8E14A95D59FF523138F751B4519.sk1.us-east-1.eks.amazonaws.com/",
                      publicEndpoint: "https://pac-staging-us.upp.ft.com"
              )
      ]
      glbMap = [
              (ClusterType.PUBLISHING.toString()): "https://upp-staging-publish-glb.upp.ft.com",
              (ClusterType.DELIVERY.toString())  : "https://upp-staging-delivery-glb.upp.ft.com"
      ]
    }

    Environment prodEnv = new Environment(PROD_NAME, pacCluster)
    prodEnv.with {
      slackChannel = SLACK_CHANNEL
      regions = [Region.EU, Region.US]
      associatedClusterTypes = [ClusterType.PAC]
      clusterToApiServerMap = [
              ("${Region.EU}-${ClusterType.PAC}".toString()): newEksEntry(
                      eksClusterName: "eks-pac-prod-eu",
                      apiServer: "https://5A5C7C1E6930A8CFC4A7F234295AFEFA.yl4.eu-west-1.eks.amazonaws.com",
                      publicEndpoint: "https://pac-prod-eu.upp.ft.com"
              ),
              ("${Region.US}-${ClusterType.PAC}".toString()): newEksEntry(
                      eksClusterName: "eks-pac-prod-us",
                      apiServer: "https://4A182804B4F5AE3680997EE1931F727C.gr7.us-east-1.eks.amazonaws.com",
                      publicEndpoint: "https://pac-prod-us.upp.ft.com"
              ),
      ]
      glbMap = [
              (ClusterType.PUBLISHING.toString()): "https://upp-prod-publish-glb.upp.ft.com",
              (ClusterType.DELIVERY.toString())  : "https://upp-prod-delivery-glb.upp.ft.com"
      ]
    }
    pacCluster.environments = [stagingEnv, prodEnv]
    pacCluster
  }
}