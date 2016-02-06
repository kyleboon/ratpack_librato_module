package ratpack.librato;

import java.io.Serializable;

public class LibratoConfig implements Serializable {
  private long timeIntervalInSeconds = 10;
  private String email;
  private String token;
  private String sourceIdentifier;
  private boolean omitComplexGauges = false;
  private boolean deleteIdleStats = true;

  public boolean getIsValid() {
    return email != null && 
    token != null && 
    sourceIdentifier != null && 
    email.length() > 0 &&
    token.length() > 0 &&
    sourceIdentifier.length() > 0;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getSourceIdentifier() {
    return sourceIdentifier;
  }

  public void setSourceIdentifier(String sourceIdentifier) {
    this.sourceIdentifier = sourceIdentifier;
  }

  public long getTimeIntervalInSeconds() { return timeIntervalInSeconds; }

  public void setTimeIntervalInSeconds(long timeIntervalInSeconds) {
    this.timeIntervalInSeconds = timeIntervalInSeconds;
  }

  public boolean getOmitComplexGauges() {
    return omitComplexGauges;
  }

  public boolean isOmitComplexGauges() {
    return omitComplexGauges;
  }

  public void setOmitComplexGauges(boolean omitComplexGauges) {
    this.omitComplexGauges = omitComplexGauges;
  }

  public boolean getDeleteIdleStats() {
    return deleteIdleStats;
  }

  public boolean isDeleteIdleStats() {
    return deleteIdleStats;
  }

  public void setDeleteIdleStats(boolean deleteIdleStats) {
    this.deleteIdleStats = deleteIdleStats;
  }
}
