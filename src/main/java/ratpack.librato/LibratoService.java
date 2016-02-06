package ratpack.librato;

import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.librato.metrics.LibratoReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.server.Service;
import ratpack.server.StartEvent;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class LibratoService implements Service {
  private final static Logger LOG = LoggerFactory.getLogger(LibratoService.class);
  private final LibratoConfig config;
  private final MetricRegistry metricRegistry;

  public LibratoService(LibratoConfig libratoConfig, final MetricRegistry metricRegistry) {
    this.config = libratoConfig;
    this.metricRegistry = metricRegistry;
  }

  @Override
  public void onStart(StartEvent event) throws Exception {
    if (config.getIsValid()) {
      LibratoReporter.Builder builder = LibratoReporter.builder(
              metricRegistry,
              config.getEmail(),
              config.getToken(),
              config.getSourceIdentifier());

      Optional<LibratoReporter.MetricExpansionConfig> metricExpansionConfigOptional =
              event.getRegistry().maybeGet(LibratoReporter.MetricExpansionConfig.class);

      metricExpansionConfigOptional.ifPresent(mec -> { builder.setExpansionConfig(mec);});

      Optional<MetricFilter> metricFilterOptional = event.getRegistry().maybeGet(MetricFilter.class);

      metricFilterOptional.ifPresent(mf -> { builder.setFilter(mf);});

      builder.setOmitComplexGauges(config.getOmitComplexGauges());
      builder.setDeleteIdleStats(config.getDeleteIdleStats());

      LibratoReporter.enable(
              builder,
              config.getTimeIntervalInSeconds(),
              TimeUnit.SECONDS);

      LOG.info("Librato Metrics reporter configured.");
    } else {
      LOG.warn("Librato Metrics Reporter not configured.");
    }
  }
}
