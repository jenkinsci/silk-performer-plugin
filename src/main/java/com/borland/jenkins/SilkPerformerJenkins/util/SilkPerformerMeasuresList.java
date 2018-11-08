////////////////////////////////////////////////////////////
//
// GENERATED, DO NOT MODIFY
//
////////////////////////////////////////////////////////////

package com.borland.jenkins.SilkPerformerJenkins.util;

import java.util.ArrayList;
import java.util.List;

public class SilkPerformerMeasuresList
{
  private SilkPerformerMeasuresList()
  {
  }

  static final List<SilkPerformerMeasure> measures;
  static
  {
    measures = new ArrayList<>();
    measures.add(new SilkPerformerMeasure(67, "Request data sent[kB]", 1, "IIOP"));
    measures.add(new SilkPerformerMeasure(2, "Server busy time[s]", 1, "IIOP"));
    measures.add(new SilkPerformerMeasure(68, "Response data received[kB]", 1, "IIOP"));
    measures.add(new SilkPerformerMeasure(1, "Round trip time [s]", 1, "IIOP"));
    measures.add(new SilkPerformerMeasure(3, "Response time[s]", 2, "Timer"));
    measures.add(new SilkPerformerMeasure(152, "Busy time[s]", 2, "Timer"));
    measures.add(new SilkPerformerMeasure(72, "Custom counter", 3, "Counter"));
    measures.add(new SilkPerformerMeasure(4, "Custom counter", 3, "Counter"));
    measures.add(new SilkPerformerMeasure(7, "ExecDirect[s]", 4, "SQL"));
    measures.add(new SilkPerformerMeasure(6, "Exec [s]", 4, "SQL"));
    measures.add(new SilkPerformerMeasure(5, "Parse[s]", 4, "SQL"));
    measures.add(new SilkPerformerMeasure(9, "Trans. failed[s]", 5, "Transaction"));
    measures.add(new SilkPerformerMeasure(136, "Trans.(busy) ok[s]", 5, "Transaction"));
    measures.add(new SilkPerformerMeasure(138, "Trans.(busy) canceled[s]", 5, "Transaction"));
    measures.add(new SilkPerformerMeasure(8, "Trans. ok[s]", 5, "Transaction"));
    measures.add(new SilkPerformerMeasure(137, "Trans.(busy) failed[s]", 5, "Transaction"));
    measures.add(new SilkPerformerMeasure(10, "Trans. canceled[s]", 5, "Transaction"));
    measures.add(new SilkPerformerMeasure(14, "Hits failed", 6, "Web Form"));
    measures.add(new SilkPerformerMeasure(15, "Round trip time [s]", 6, "Web Form"));
    measures.add(new SilkPerformerMeasure(16, "Server busy time[s]", 6, "Web Form"));
    measures.add(new SilkPerformerMeasure(13, "Hits", 6, "Web Form"));
    measures.add(new SilkPerformerMeasure(11, "Request data sent[kB]", 6, "Web Form"));
    measures.add(new SilkPerformerMeasure(12, "Response data received[kB]", 6, "Web Form"));
    measures.add(new SilkPerformerMeasure(18, "Response data received[kB]", 7, "Tuxedo"));
    measures.add(new SilkPerformerMeasure(19, "Response time[s]", 7, "Tuxedo"));
    measures.add(new SilkPerformerMeasure(17, "Request data sent[kB]", 7, "Tuxedo"));
    measures.add(new SilkPerformerMeasure(160, "Running users", 8, "Controller"));
    measures.add(new SilkPerformerMeasure(24, "Request data sent[kB]", 8, "Controller"));
    measures.add(new SilkPerformerMeasure(27, "Http hits", 8, "Controller"));
    measures.add(new SilkPerformerMeasure(161, "Running users", 8, "Controller"));
    measures.add(new SilkPerformerMeasure(20, "Active users", 8, "Controller"));
    measures.add(new SilkPerformerMeasure(23, "Errors", 8, "Controller"));
    measures.add(new SilkPerformerMeasure(26, "Concurrent connections", 8, "Controller"));
    measures.add(new SilkPerformerMeasure(21, "Transactions", 8, "Controller"));
    measures.add(new SilkPerformerMeasure(22, "SQL commands", 8, "Controller"));
    measures.add(new SilkPerformerMeasure(145, "Active users - BDLT", 8, "Controller"));
    measures.add(new SilkPerformerMeasure(146, "Active users - Web", 8, "Controller"));
    measures.add(new SilkPerformerMeasure(25, "Response data received[kB]", 8, "Controller"));
    measures.add(new SilkPerformerMeasure(69, "Invocation interval [s]", 11, "IIOP Callback"));
    measures.add(new SilkPerformerMeasure(70, "Response data sent[kB]", 11, "IIOP Callback"));
    measures.add(new SilkPerformerMeasure(71, "Request data received[kB]", 11, "IIOP Callback"));
    measures.add(new SilkPerformerMeasure(147, "Active users - BDLT", 12, "Summary General"));
    measures.add(new SilkPerformerMeasure(148, "Active users - Web", 12, "Summary General"));
    measures.add(new SilkPerformerMeasure(83, "Transactions", 12, "Summary General"));
    measures.add(new SilkPerformerMeasure(82, "Active users", 12, "Summary General"));
    measures.add(new SilkPerformerMeasure(84, "Errors", 12, "Summary General"));
    measures.add(new SilkPerformerMeasure(85, "SQL commands", 13, "Summary Database"));
    measures.add(new SilkPerformerMeasure(86, "Open database logins", 13, "Summary Database"));
    measures.add(new SilkPerformerMeasure(87, "Open database cursors", 13, "Summary Database"));
    measures.add(new SilkPerformerMeasure(91, "Requests failed", 14, "Summary Internet"));
    measures.add(new SilkPerformerMeasure(94, "Connects successful", 14, "Summary Internet"));
    measures.add(new SilkPerformerMeasure(89, "Response data received[kB]", 14, "Summary Internet"));
    measures.add(new SilkPerformerMeasure(95, "Connects failed ", 14, "Summary Internet"));
    measures.add(new SilkPerformerMeasure(88, "Request data sent[kB]", 14, "Summary Internet"));
    measures.add(new SilkPerformerMeasure(93, "Responses failed", 14, "Summary Internet"));
    measures.add(new SilkPerformerMeasure(92, "Responses received", 14, "Summary Internet"));
    measures.add(new SilkPerformerMeasure(90, "Requests sent", 14, "Summary Internet"));
    measures.add(new SilkPerformerMeasure(130, "Concurrent Connections", 14, "Summary Internet"));
    measures.add(new SilkPerformerMeasure(96, "Connects retries", 14, "Summary Internet"));
    measures.add(new SilkPerformerMeasure(98, "Http re-authentications", 15, "Summary Web"));
    measures.add(new SilkPerformerMeasure(105, "Http cache cond. reloads", 15, "Summary Web"));
    measures.add(new SilkPerformerMeasure(104, "Http cache hits", 15, "Summary Web"));
    measures.add(new SilkPerformerMeasure(111, "Http request retries", 15, "Summary Web"));
    measures.add(new SilkPerformerMeasure(99, "Http hits", 15, "Summary Web"));
    measures.add(new SilkPerformerMeasure(102, "Http cookies sent", 15, "Summary Web"));
    measures.add(new SilkPerformerMeasure(101, "Http cookies received", 15, "Summary Web"));
    measures.add(new SilkPerformerMeasure(103, "Hits failed", 15, "Summary Web"));
    measures.add(new SilkPerformerMeasure(109, "Http 4xx responses", 15, "Summary Web"));
    measures.add(new SilkPerformerMeasure(110, "Http 5xx responses", 15, "Summary Web"));
    measures.add(new SilkPerformerMeasure(97, "Http redirections", 15, "Summary Web"));
    measures.add(new SilkPerformerMeasure(100, "Http pages", 15, "Summary Web"));
    measures.add(new SilkPerformerMeasure(106, "Http 1xx responses", 15, "Summary Web"));
    measures.add(new SilkPerformerMeasure(107, "Http 2xx responses", 15, "Summary Web"));
    measures.add(new SilkPerformerMeasure(108, "Http 3xx responses", 15, "Summary Web"));
    measures.add(new SilkPerformerMeasure(125, "IIOP loc request timed out", 16, "Summary Iiop"));
    measures.add(new SilkPerformerMeasure(115, "IIOP fragments sent", 16, "Summary Iiop"));
    measures.add(new SilkPerformerMeasure(124, "IIOP fragments received", 16, "Summary Iiop"));
    measures.add(new SilkPerformerMeasure(112, "IIOP messages", 16, "Summary Iiop"));
    measures.add(new SilkPerformerMeasure(122, "IIOP loc replies obj moved", 16, "Summary Iiop"));
    measures.add(new SilkPerformerMeasure(116, "IIOP replies ok", 16, "Summary Iiop"));
    measures.add(new SilkPerformerMeasure(113, "IIOP requests", 16, "Summary Iiop"));
    measures.add(new SilkPerformerMeasure(118, "IIOP replies obj moved", 16, "Summary Iiop"));
    measures.add(new SilkPerformerMeasure(121, "IIOP loc replies obj unknown", 16, "Summary Iiop"));
    measures.add(new SilkPerformerMeasure(120, "IIOP loc replies obj here", 16, "Summary Iiop"));
    measures.add(new SilkPerformerMeasure(114, "IIOP loc requests", 16, "Summary Iiop"));
    measures.add(new SilkPerformerMeasure(123, "IIOP message errors", 16, "Summary Iiop"));
    measures.add(new SilkPerformerMeasure(119, "IIOP request timed out", 16, "Summary Iiop"));
    measures.add(new SilkPerformerMeasure(117, "IIOP replies exception", 16, "Summary Iiop"));
    measures.add(new SilkPerformerMeasure(127, "Memory", 17, "Health Control"));
    measures.add(new SilkPerformerMeasure(126, "CPU", 17, "Health Control"));
    measures.add(new SilkPerformerMeasure(128, "Responsiveness", 17, "Health Control"));
    measures.add(new SilkPerformerMeasure(150, "System Health", 17, "Health Control"));
    measures.add(new SilkPerformerMeasure(149, "App Health", 17, "Health Control"));
    measures.add(new SilkPerformerMeasure(129, "Realtime Counter", 18, "Server"));
    measures.add(new SilkPerformerMeasure(133, "Server busy time[s]", 19, "Page and Action Timer"));
    measures.add(new SilkPerformerMeasure(165, "Load end[s]", 19, "Page and Action Timer"));
    measures.add(new SilkPerformerMeasure(151, "Action time[s]", 19, "Page and Action Timer"));
    measures.add(new SilkPerformerMeasure(162, "First paint[s]", 19, "Page and Action Timer"));
    measures.add(new SilkPerformerMeasure(166, "Time To Interact[s]", 19, "Page and Action Timer"));
    measures.add(new SilkPerformerMeasure(134, "Page data[kB]", 19, "Page and Action Timer"));
    measures.add(new SilkPerformerMeasure(131, "Page time[s]", 19, "Page and Action Timer"));
    measures.add(new SilkPerformerMeasure(135, "Embedded objects data[kB]", 19, "Page and Action Timer"));
    measures.add(new SilkPerformerMeasure(132, "Document download time[s]", 19, "Page and Action Timer"));
    measures.add(new SilkPerformerMeasure(163, "DOM complete[s]", 19, "Page and Action Timer"));
    measures.add(new SilkPerformerMeasure(164, "DOM interactive[s]", 19, "Page and Action Timer"));
    measures.add(new SilkPerformerMeasure(139, "Citrix synchronization points", 20, "Summary Citrix"));
    measures.add(new SilkPerformerMeasure(140, "SapGui requests", 21, "Summary SapGui"));
    measures.add(new SilkPerformerMeasure(141, "Round trips", 22, "SapGui"));
    measures.add(new SilkPerformerMeasure(142, "Interpretation Time [s]", 22, "SapGui"));
    measures.add(new SilkPerformerMeasure(143, "Flushes", 22, "SapGui"));
    measures.add(new SilkPerformerMeasure(144, "Response time [s]", 22, "SapGui"));
    measures.add(new SilkPerformerMeasure(159, "Stream size", 23, "Video"));
    measures.add(new SilkPerformerMeasure(153, "Actual video duration", 23, "Video"));
    measures.add(new SilkPerformerMeasure(157, "Segments critical D/P ratio", 23, "Video"));
    measures.add(new SilkPerformerMeasure(155, "Overall download time", 23, "Video"));
    measures.add(new SilkPerformerMeasure(158, "Segments", 23, "Video"));
    measures.add(new SilkPerformerMeasure(154, "Time to first segment", 23, "Video"));
    measures.add(new SilkPerformerMeasure(156, "Stream D/P ratio", 23, "Video"));
    measures.add(new SilkPerformerMeasure(174, "AppHealth internal 4", 24, "Health Control Detail"));
    measures.add(new SilkPerformerMeasure(173, "AppHealth internal 3", 24, "Health Control Detail"));
    measures.add(new SilkPerformerMeasure(170, "Responsiveness internal 4", 24, "Health Control Detail"));
    measures.add(new SilkPerformerMeasure(169, "Responsiveness internal 3", 24, "Health Control Detail"));
    measures.add(new SilkPerformerMeasure(168, "Responsiveness internal 2", 24, "Health Control Detail"));
    measures.add(new SilkPerformerMeasure(167, "Responsiveness internal 1", 24, "Health Control Detail"));
    measures.add(new SilkPerformerMeasure(172, "AppHealth internal 2", 24, "Health Control Detail"));
    measures.add(new SilkPerformerMeasure(171, "AppHealth internal 1", 24, "Health Control Detail"));
  }

  public static class SilkPerformerMeasure
  {
    final int measureCategoryId;
    final String measureCategoryName;
    final int measureTypeId;
    final String measureTypeName;

    public SilkPerformerMeasure(int measureTypeId, String measureTypeName, int measureCategoryId, String measureCategoryName)
    {
      this.measureTypeId = measureTypeId;
      this.measureTypeName = measureTypeName;
      this.measureCategoryId = measureCategoryId;
      this.measureCategoryName = measureCategoryName;
    }

    public int getMeasureCategoryId()
    {
      return measureCategoryId;
    }

    public String getMeasureCategoryName()
    {
      return measureCategoryName;
    }

    public int getMeasureTypeId()
    {
      return measureTypeId;
    }

    public String getMeasureTypeName()
    {
      return measureTypeName;
    }
  }
}
