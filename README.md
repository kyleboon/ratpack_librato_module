Ratpack Librato Module
===============

This integrates the [reporter for Dropwizard Metrics](https://github.com/librato/metrics-librato) to Librato into 
ratpack. 


```
ratpack {
  serverConfig {
    config -> config
      .baseDir(BaseDir.find())
      .props("app.properties")
      .env()
      .sysProps()
      .props(HerokuLibratoConfigUtility.getLibratoProperties())
      .require("/metrics", DropwizardMetricsConfig)
      .require("/librato", LibratoConfig)
  }

  bindings {
    module DropwizardMetricsModule
    module LibratoModule

    bindInstance Service, new Service() {
      @Override
      void onStart(StartEvent event) throws Exception {
        RxRatpack.initialize()
      }
    }
  }
}
```