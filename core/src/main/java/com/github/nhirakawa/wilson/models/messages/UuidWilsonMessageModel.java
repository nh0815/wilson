package com.github.nhirakawa.wilson.models.messages;

import java.util.UUID;

import org.immutables.value.Value;
import org.immutables.value.Value.Immutable;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.nhirakawa.wilson.models.style.WilsonStyle;

@Immutable
@WilsonStyle
@JsonTypeName("UuidMessage")
public interface UuidWilsonMessageModel extends SerializedWilsonMessage {

  @Value.Default
  default String getUuid() {
    return UUID.randomUUID().toString();
  }
}