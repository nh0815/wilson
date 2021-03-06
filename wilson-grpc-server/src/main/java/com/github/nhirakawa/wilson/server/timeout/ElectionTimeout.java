package com.github.nhirakawa.wilson.server.timeout;

import com.github.nhirakawa.wilson.models.ClusterMember;
import com.github.nhirakawa.wilson.models.messages.ElectionTimeoutMessage;
import com.github.nhirakawa.wilson.server.config.ConfigPath;
import com.github.nhirakawa.wilson.server.dagger.LocalMember;
import com.github.nhirakawa.wilson.server.raft.StateMachineMessageApplier;
import com.typesafe.config.Config;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ElectionTimeout extends BaseTimeout {
  private final StateMachineMessageApplier messageApplier;

  @Inject
  ElectionTimeout(
    ScheduledExecutorService scheduledExecutorService,
    Config config,
    StateMachineMessageApplier messageApplier,
    @LocalMember ClusterMember clusterMember
  ) {
    super(
      scheduledExecutorService,
      config.getLong(ConfigPath.WILSON_ELECTION_TIMEOUT.getPath()),
      clusterMember
    );
    this.messageApplier = messageApplier;
  }

  @Override
  protected void doTimeout() {
    messageApplier.apply(
      ElectionTimeoutMessage.builder().setElectionTimeout(getPeriod()).build()
    );
  }
}
