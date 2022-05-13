package com.fet.telemedicine.backend.chat.xmpp;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * A connection contains common information needed to connect to an XMPP server and sign in.
 */
@ConfigurationProperties(prefix = "fet.telemedicine.chat.xmpp")
public class XMPPProperties {

  /**
   * The address of the server.
   */
  private String host;

  /**
   * The port to use (usually 5222).
   */
  private int port;

  /**
   * The XMPP domain is what follows after the '@' sign in XMPP addresses (JIDs).
   */
  private String domain;

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

}
