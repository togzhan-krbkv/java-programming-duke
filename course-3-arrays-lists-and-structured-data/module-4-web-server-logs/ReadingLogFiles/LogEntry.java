/**
 * LogEntry — represents a single entry (one visit) from a web server log:
 * the visitor's IP address, access time, request, HTTP status code, and
 * number of bytes returned.
 *
 * @author Togzhan K.
 */
import java.util.*;

public class LogEntry
{
    private String ipAddress;
    private Date accessTime;
    private String request;
    private int statusCode;
    private int bytesReturned;

    public LogEntry(String ip, Date time, String req, int status, int bytes)
    {
        ipAddress = ip;
        accessTime = time;
        request = req;
        statusCode = status;
        bytesReturned = bytes;
    }

    public String getIpAddress()
    {
        return ipAddress;
    }

    public Date getAccessTime()
    {
        return accessTime;
    }

    public String getRequest()
    {
        return request;
    }

    public int getStatusCode()
    {
        return statusCode;
    }

    public int getBytesReturned()
    {
        return bytesReturned;
    }

    public String toString()
    {
        return ipAddress + " " + accessTime + " " + request
                + " " + statusCode + " " + bytesReturned;
    }
}
