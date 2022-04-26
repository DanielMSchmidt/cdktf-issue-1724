package com.mycompany.app;

import software.constructs.Construct;

import com.hashicorp.cdktf.App;
import com.hashicorp.cdktf.TerraformStack;
import imports.digitalocean.*;

public class Main extends TerraformStack
{
    public Main(final Construct scope, final String id) {
        super(scope, id);

        new DigitaloceanProvider(this, "provider");

        KubernetesCluster.Builder.create (this, "dto.getClusterName ()")
        .name ("dto.getClusterName ()")
        .vpcUuid ("vpc.getId ()")
        .version ("dto.getK8sVersion ()")
        .region ("dto.getRegion ()")
        .autoUpgrade (true)
        .surgeUpgrade (true)
        .ha (true)
        .nodePool (KubernetesClusterNodePool.builder ()
                .name ("default-node-group")
                .nodeCount (4)
                .size ("s-2vcpu-2gb")
                .build ())
        .build ();
    }

    public static void main(String[] args) {
        final App app = new App();
        new Main(app, "cdktf-issue-1724");
        app.synth();
    }
}