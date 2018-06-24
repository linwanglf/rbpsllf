<html xmlns:v="urn:schemas-microsoft-com:vml"
      xmlns:o="urn:schemas-microsoft-com:office:office"
      xmlns:x="urn:schemas-microsoft-com:office:excel"
      xmlns="http://www.w3.org/TR/REC-html40">


<%@page language="java" import="java.lang.*,java.sql.*,java.util.*" %>

<%@ page language="java" pageEncoding="gb2312"%>
<%@ page contentType="text/html;charset=gb2312"%>

<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>

<head>
    <TITLE>设备管理科-月度加班打印</TITLE>

    <meta name=ProgId content=Excel.Sheet>
    <meta name=Generator content="Microsoft Excel 10">
    <link rel=File-List href="Page.files/filelist.xml">
    <!--[if !mso]>
    <style>
        v\:* {behavior:url(#default#VML);}
        o\:* {behavior:url(#default#VML);}
        x\:* {behavior:url(#default#VML);}
        .shape {behavior:url(#default#VML);}
    </style>
    <![endif]-->
    <style id="员工加班审批表_32533_Styles">
        <!--table
        {mso-displayed-decimal-separator:"\.";
            mso-displayed-thousand-separator:"\,";}
        .font532533
        {color:windowtext;
            font-size:9.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;}
        .font632533
        {color:windowtext;
            font-size:18.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;}
        .font732533
        {color:windowtext;
            font-size:10.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;}
        .font832533
        {color:windowtext;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;}
        .xl1532533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:12.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            vertical-align:middle;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl2332533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:12.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            vertical-align:middle;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl2432533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:12.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:center;
            vertical-align:middle;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl2532533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:18.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:underline;
            text-underline-style:single;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            vertical-align:middle;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl2632533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:12.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:left;
            vertical-align:middle;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl2732533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:18.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:underline;
            text-underline-style:single;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:center;
            vertical-align:middle;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl2832533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:center;
            vertical-align:middle;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl2932533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:center;
            vertical-align:middle;
            border:.5pt solid windowtext;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl3032533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:center;
            vertical-align:middle;
            border:.5pt solid windowtext;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:normal;}
        .xl3132533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            vertical-align:middle;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl3232533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:center;
            vertical-align:middle;
            border-top:none;
            border-right:.5pt solid windowtext;
            border-bottom:.5pt solid windowtext;
            border-left:.5pt solid windowtext;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:normal;}
        .xl3332533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:center;
            vertical-align:middle;
            border-top:.5pt solid windowtext;
            border-right:.5pt solid windowtext;
            border-bottom:.5pt solid windowtext;
            border-left:1.5pt solid windowtext;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:normal;}
        .xl3432533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:center;
            vertical-align:middle;
            border-top:none;
            border-right:.5pt solid windowtext;
            border-bottom:.5pt solid windowtext;
            border-left:none;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:normal;}
        .xl3532533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:left;
            vertical-align:middle;
            border:.5pt solid windowtext;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:normal;}
        .xl3632533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            vertical-align:middle;
            border:.5pt solid windowtext;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl3732533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:center;
            vertical-align:middle;
            border-top:.5pt solid windowtext;
            border-right:1.5pt solid windowtext;
            border-bottom:.5pt solid windowtext;
            border-left:.5pt solid windowtext;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl3832533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:12.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            vertical-align:middle;
            border:.5pt solid windowtext;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl3932533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:"\@";
            text-align:center;
            vertical-align:middle;
            border-top:none;
            border-right:.5pt solid windowtext;
            border-bottom:.5pt solid windowtext;
            border-left:.5pt solid windowtext;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl4032533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:12.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            vertical-align:middle;
            border-top:.5pt solid windowtext;
            border-right:.5pt solid windowtext;
            border-bottom:.5pt solid windowtext;
            border-left:none;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl4132533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:center;
            vertical-align:middle;
            border-top:.5pt solid windowtext;
            border-right:.5pt solid windowtext;
            border-bottom:.5pt solid windowtext;
            border-left:1.5pt solid windowtext;
            mso-diagonal-up:.5pt solid black;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:normal;}
        .xl4232533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            vertical-align:middle;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl4332533
        {padding:0px;
            mso-ignore:padding;
            color:black;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:"\@";
            text-align:center;
            vertical-align:middle;
            border:.5pt solid windowtext;
            background:white;
            mso-pattern:auto none;
            white-space:normal;}
        .xl4432533
        {padding:0px;
            mso-ignore:padding;
            color:black;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:"\@";
            text-align:center;
            vertical-align:middle;
            border:.5pt solid windowtext;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:normal;}
        .xl4532533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:12.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            vertical-align:middle;
            border-top:none;
            border-right:.5pt solid windowtext;
            border-bottom:.5pt solid windowtext;
            border-left:.5pt solid windowtext;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl4632533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:"\@";
            text-align:center;
            vertical-align:middle;
            border:.5pt solid windowtext;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl4732533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:12.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:center;
            vertical-align:middle;
            border:.5pt solid windowtext;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl4832533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            vertical-align:middle;
            border:.5pt solid windowtext;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:normal;}
        .xl4932533
        {padding:0px;
            mso-ignore:padding;
            color:black;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:center;
            vertical-align:middle;
            border:.5pt solid windowtext;
            background:white;
            mso-pattern:auto none;
            white-space:normal;}
        .xl5032533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:12.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:center;
            vertical-align:middle;
            border-top:none;
            border-right:none;
            border-bottom:.5pt solid windowtext;
            border-left:none;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl5132533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:center;
            vertical-align:middle;
            border-top:.5pt solid windowtext;
            border-right:.5pt solid windowtext;
            border-bottom:.5pt solid windowtext;
            border-left:1.5pt solid windowtext;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl5232533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:center;
            vertical-align:middle;
            border-top:.5pt solid windowtext;
            border-right:.5pt solid windowtext;
            border-bottom:none;
            border-left:.5pt solid windowtext;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:normal;}
        .xl5332533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:center;
            vertical-align:middle;
            border-top:.5pt solid windowtext;
            border-right:1.5pt solid windowtext;
            border-bottom:none;
            border-left:.5pt solid windowtext;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:normal;}
        .xl5432533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:center;
            vertical-align:middle;
            border-top:none;
            border-right:1.5pt solid windowtext;
            border-bottom:.5pt solid windowtext;
            border-left:.5pt solid windowtext;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:normal;}
        .xl5532533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:center;
            vertical-align:middle;
            border-top:.5pt solid windowtext;
            border-right:.5pt solid windowtext;
            border-bottom:none;
            border-left:.5pt solid windowtext;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl5632533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:center;
            vertical-align:middle;
            border-top:none;
            border-right:.5pt solid windowtext;
            border-bottom:none;
            border-left:.5pt solid windowtext;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl5732533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:center;
            vertical-align:middle;
            border-top:none;
            border-right:.5pt solid windowtext;
            border-bottom:.5pt solid windowtext;
            border-left:.5pt solid windowtext;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl5832533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:center;
            vertical-align:middle;
            border-top:.5pt solid windowtext;
            border-right:none;
            border-bottom:.5pt solid windowtext;
            border-left:1.5pt solid windowtext;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl5932533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:center;
            vertical-align:middle;
            border-top:.5pt solid windowtext;
            border-right:none;
            border-bottom:.5pt solid windowtext;
            border-left:none;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl6032533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:center;
            vertical-align:middle;
            border-top:.5pt solid windowtext;
            border-right:1.5pt solid windowtext;
            border-bottom:.5pt solid windowtext;
            border-left:none;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl6132533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:left;
            vertical-align:middle;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl6232533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:center;
            vertical-align:middle;
            border-top:.5pt solid windowtext;
            border-right:none;
            border-bottom:.5pt solid windowtext;
            border-left:.5pt solid windowtext;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        .xl6332533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:center;
            vertical-align:middle;
            border-top:none;
            border-right:.5pt solid windowtext;
            border-bottom:none;
            border-left:.5pt solid windowtext;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:normal;}
        .xl6432533
        {padding:0px;
            mso-ignore:padding;
            color:windowtext;
            font-size:14.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-number-format:General;
            text-align:center;
            vertical-align:middle;
            border-top:.5pt solid windowtext;
            border-right:none;
            border-bottom:none;
            border-left:none;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;}
        ruby
        {ruby-align:left;}
        rt
        {color:windowtext;
            font-size:9.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-char-type:none;}
        -->
    </style>
</head>

<body>
<!--[if !excel]>　　<![endif]-->
<!--下列信息由 Microsoft Excel 的“发布为 Web 页”向导生成。-->
<!--如果同一条目从 Excel 中重新发布，则所有位于 DIV 标记之间的信息均将被替换。-->
<!----------------------------->
<!--“从 EXCEL 发布 Web 页”向导开始-->
<!----------------------------->

<div id="员工加班审批表_32533" align=center x:publishsource="Excel">


    <table x:str border=0 cellpadding=0 cellspacing=0 width=1081 style='border-collapse:
 collapse;table-layout:fixed;width:814pt'>
        <col class=xl2432533 width=28 style='mso-width-source:userset;mso-width-alt:
 896;width:21pt'>
        <col class=xl2432533 width=61 style='mso-width-source:userset;mso-width-alt:
 1952;width:46pt'>
        <col class=xl2432533 width=72 style='width:54pt'>
        <col class=xl2432533 width=60 style='mso-width-source:userset;mso-width-alt:
 1920;width:45pt'>
        <col width=123 style='mso-width-source:userset;mso-width-alt:3936;width:92pt'>
        <col width=25 span=3 style='mso-width-source:userset;mso-width-alt:800;
 width:19pt'>
        <col width=37 style='mso-width-source:userset;mso-width-alt:1184;width:28pt'>
        <col width=132 style='mso-width-source:userset;mso-width-alt:4224;width:99pt'>
        <col width=49 span=2 style='mso-width-source:userset;mso-width-alt:1568;
 width:37pt'>
        <col width=121 style='mso-width-source:userset;mso-width-alt:3872;width:91pt'>
        <col width=25 span=3 style='mso-width-source:userset;mso-width-alt:800;
 width:19pt'>
        <col width=37 style='mso-width-source:userset;mso-width-alt:1184;width:28pt'>
        <col width=68 style='mso-width-source:userset;mso-width-alt:2176;width:51pt'>
        <col width=54 style='mso-width-source:userset;mso-width-alt:1728;width:41pt'>
        <col width=40 style='mso-width-source:userset;mso-width-alt:1280;width:30pt'>


        <tr height=37 style='mso-height-source:userset;height:27.75pt'>
            <td height=37 width=28 style='height:27.75pt;width:21pt' align=left valign=top colspan=4>

                <img width=191 height=39 src="images/LOGO2.gif" >



        </tr>
        </td>
        <td class=xl2332533 width=123 style='width:92pt'></td>
        <td class=xl2332533 width=25 style='width:19pt'></td>
        <td class=xl2332533 width=25 style='width:19pt'></td>
        <td class=xl2332533 width=25 style='width:19pt'></td>
        <td class=xl2332533 width=37 style='width:28pt'></td>
        <td class=xl2332533 width=132 style='width:99pt'></td>
        <td height=37 class=xl2332533 width=49 style='height:27.75pt;width:37pt'></td>
        <td class=xl2332533 width=49 style='width:37pt'></td>
        <td class=xl1532533 width=121 style='width:91pt'></td>
        <td class=xl1532533 width=25 style='width:19pt'></td>
        <td class=xl1532533 width=25 style='width:19pt'></td>
        <td class=xl1532533 width=25 style='width:19pt'></td>
        <td class=xl1532533 width=37 style='width:28pt'></td>
        <td class=xl1532533 width=68 style='width:51pt'></td>
        <td class=xl1532533 width=54 style='width:41pt'></td>
        <td class=xl1532533 width=40 style='width:30pt'></td>
        </tr>



        <tr height=30 style='height:22.5pt'>
            <td height=30 class=xl2432533 style='height:22.5pt'></td>
            <td class=xl2432533></td>
            <td class=xl2532533></td>
            <td class=xl2532533 colspan=9><span style='mso-spacerun:yes'>&nbsp;&nbsp;
  </span>设备科<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span><font
                    class=font632533>部员工加班审批表（计划内）</font></td>
            <td class=xl2532533></td>
            <td class=xl2532533></td>
            <td class=xl2532533></td>
            <td class=xl2532533></td>
            <td class=xl2532533></td>
            <td class=xl2632533></td>
            <td class=xl2532533></td>
            <td class=xl1532533></td>
        </tr>
        <tr height=30 style='height:22.5pt'>
            <td height=30 class=xl2732533 style='height:22.5pt'></td>
            <td colspan=3 class=xl5032533>　</td>
            <td class=xl2732533></td>
            <td class=xl2732533></td>
            <td class=xl2732533></td>
            <td class=xl2732533></td>
            <td class=xl2732533></td>
            <td class=xl2732533></td>
            <td class=xl2732533></td>
            <td class=xl2732533></td>
            <td class=xl2832533>单位：小时</td>
            <td class=xl2732533></td>
            <td class=xl2732533></td>
            <td class=xl2732533></td>
            <td class=xl2732533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
        </tr>
        <tr height=19 style='mso-height-source:userset;height:14.25pt'>
            <td rowspan=3 height=83 class=xl5232533 width=28 style='border-bottom:.5pt solid black;
  height:62.25pt;width:21pt'>序号</td>
            <td rowspan=3 class=xl5232533 width=61 style='border-bottom:.5pt solid black;
  border-top:none;width:46pt'>员工编号</td>
            <td rowspan=3 class=xl2932533 style='border-top:none'>姓名</td>
            <td rowspan=3 class=xl2932533 style='border-top:none'>科</td>
            <td colspan=8 class=xl5832533 style='border-right:1.5pt solid black'>加班计划审批</td>
            <td colspan=6 class=xl5132533 style='border-left:none'>加班结果确认（加班实施后审批）</td>
            <td rowspan=3 class=xl3032533 width=54 style='width:41pt'>加班费</td>
            <td rowspan=3 class=xl5532533 style='border-bottom:.5pt solid black'>补休</td>
        </tr>
        <tr class=xl3132533 height=16 style='mso-height-source:userset;height:12.0pt'>
            <td rowspan=2 height=64 class=xl5132533 style='height:48.0pt;border-top:none'>日期</td>
            <td rowspan=2 class=xl5232533 width=25 style='border-bottom:.5pt solid black;
  border-top:none;width:19pt'>加班时数</td>
            <td colspan=3 class=xl2932533 style='border-left:none'>加班类别</td>
            <td rowspan=2 class=xl2932533 style='border-top:none'>加班原因</td>
            <td rowspan=2 class=xl3032533 width=49 style='border-top:none;width:37pt' colspan="2">科负责人同意</td>

            <td rowspan=2 class=xl5132533 style='border-top:none'>日期</td>
            <td rowspan=2 class=xl5232533 width=25 style='border-bottom:.5pt solid black;
  border-top:none;width:19pt'>加班时数</td>
            <td colspan=3 class=xl2932533 style='border-left:none'>加班类别</td>
            <td rowspan=2 class=xl3032533 width=68 style='border-top:none;width:51pt'>科负责人核准</td>
        </tr>
        <tr height=48 style='mso-height-source:userset;height:36.0pt'>
            <td height=48 class=xl3032533 width=25 style='height:36.0pt;border-top:none;
  border-left:none;width:19pt'>平时</td>
            <td class=xl3032533 width=25 style='border-top:none;border-left:none;
  width:19pt'>休息日</td>
            <td class=xl3032533 width=37 style='border-top:none;border-left:none;
  width:28pt'>法定节假日</td>
            <td class=xl3032533 width=25 style='border-top:none;border-left:none;
  width:19pt'>平时</td>
            <td class=xl3032533 width=25 style='border-top:none;border-left:none;
  width:19pt'>休息日</td>
            <td class=xl3032533 width=37 style='border-top:none;border-left:none;
  width:28pt'>法定节假日</td>
        </tr>

        <%
            //	MyJspPage thisPage = new MyJspPage(pageContext,new MyDiary());

            String menber_ID=new String();
            String menber_department=new String();
            String month_search=new String();

            menber_ID=request.getParameter("menber_ID");//人员ID号
            month_search=request.getParameter("month_search");//查询月份

            //取得个人在所选择月的加班总时间（包括正常加班及临时加班）
            int  totalRec_week_houre=0;//当月各个星期的总时间
            int  totalRec_overtime_houre=0;  //当月各个临时加班的总时间
            int  totalRec_overtime_houre_have=0;  //当月各个临时加班的总时间

            int  totalRec_houre=0;//当月加班总时间

            String over_houre[]=new String[12];
            int array_no=0;

            String over_houre_total[]=new String[12];
            int array_no_total=0;



        %>



        <%-----------------序号-----------------%>
        <tr height=37 style='mso-height-source:userset;height:27.95pt'>
            <td height=37 class=xl2932533 style='height:27.95pt;border-top:none' x:num><%=333%></td>
            <%----------------- 员工编号-----------------%>
            <td class=xl4332533 width=61 style='border-top:none;border-left:none;
  width:46pt'>


            </td>
            <%----------------- 员工姓名-----------------%>
            <td class=xl4932533 width=72 style='border-top:none;border-left:none;
  width:54pt'>


                <img width=65 height=35 src="images/dd.GIF" >


            </td>
            <%----------------- 科-----------------%>
            <td class=xl2932533 style='border-top:none;border-left:none'><%=menber_department%></td>
            <%----------------- 日期-----------------%>
            <td class=xl3332533 width=123 style='border-top:none;width:92pt'>
                <FONT SIZE="1">
                    <%=1%>月
                    <%=2%>日
                    <%=3%>~
                    <%=4%>月
                    <%=5%>日
                    <%=6%>
                </FONT>
            </td>
            <%----------------- 加班时数-----------------%>
            <td class=xl3432533 width=25 style='width:19pt'>8 </td>
            <%----------------- 加班类型（平时）-----------------%>
            <td class=xl3032533 width=25 style='border-top:none;border-left:none;
  width:19pt'></td>
            <%----------------- 加班类型（休息日）-----------------%>
            <td class=xl3032533 width=25 style='border-top:none;border-left:none;
  width:19pt'>√</td>
            <%----------------- 加班类型（法定节假日）-----------------%>
            <td class=xl3032533 width=37 style='border-top:none;border-left:none;
  width:28pt'>　</td>
            <%----------------- 加班原因-----------------%>
            <td class=xl3532533 width=132 style='border-top:none;border-left:none;
  width:99pt'>　






            </td>
            <%----------------- 科负责人同意-----------------%>
            <td class=xl4832533 width=49 style='border-top:none;border-left:none;
  width:37pt' colspan="2">　</td>


            <%----------------- 日期-----------------%>
            <td class=xl3332533 width=121 style='border-top:none;width:91pt'><span
                    style='mso-spacerun:yes'>&nbsp;</span>
                <FONT SIZE="1">
                    <%=1%>月
                    <%=2%>日
                    <%=3%>~
                    <%=4%>月
                    <%=5%>日
                </FONT>
            </td>

            <%----------------- 加班时数-----------------%>
            <td class=xl3432533 width=25 style='width:19pt'>8</td>
            <%----------------- 加班类型（平时）-----------------%>
            <td class=xl3032533 width=25 style='border-top:none;border-left:none;
  width:19pt'>　</td>
            <%----------------- 加班类型（休息日）-----------------%>
            <td class=xl3032533 width=25 style='border-top:none;border-left:none;
  width:19pt'>√　</td>

            <%----------------- 加班类型（法定节假日）-----------------%>
            <td class=xl3832533 style='border-top:none;border-left:none'>　</td>
            <td class=xl4832533 width=68 style='border-top:none;border-left:none;
  width:51pt'>　</td>
            <td class=xl3832533 style='border-top:none;border-left:none'>　</td>
            <td class=xl3832533 style='border-top:none;border-left:none'>　</td>
        </tr>


        <%-----------------序号-----------------%>
        <tr height=37 style='mso-height-source:userset;height:27.95pt'>
            <td height=37 class=xl2932533 style='height:27.95pt;border-top:none' x:num><%=22%></td>
            <%----------------- 员工编号-----------------%>
            <td class=xl4332533 width=61 style='border-top:none;border-left:none;
  width:46pt'>


            </td>
            <%----------------- 员工姓名-----------------%>
            <td class=xl4932533 width=72 style='border-top:none;border-left:none;
  width:54pt'>

                <img width=65 height=35 src="images/dd.GIF" >


            </td>
            <%----------------- 科-----------------%>
            <td class=xl2932533 style='border-top:none;border-left:none'><%=menber_department%></td>
            <%----------------- 日期-----------------%>
            <td class=xl3332533 width=123 style='border-top:none;width:92pt'>
                <FONT SIZE="1">
                    <%=1%>月
                    <%=2%>日
                    <%=3%>~
                    <%=4%>月
                    <%=5%>日
                </FONT>
            </td>
            <%----------------- 加班时数-----------------%>
            <td class=xl3432533 width=25 style='width:19pt'>8 </td>
            <%----------------- 加班类型（平时）-----------------%>
            <td class=xl3032533 width=25 style='border-top:none;border-left:none;
  width:19pt'></td>
            <%----------------- 加班类型（休息日）-----------------%>
            <td class=xl3032533 width=25 style='border-top:none;border-left:none;
  width:19pt'></td>
            <%----------------- 加班类型（法定节假日）-----------------%>
            <td class=xl3032533 width=37 style='border-top:none;border-left:none;
  width:28pt'>√</td>
            <%----------------- 加班原因-----------------%>
            <td class=xl3532533 width=132 style='border-top:none;border-left:none;
  width:99pt'>　


            </td>
            <%----------------- 科负责人同意-----------------%>
            <td class=xl4832533 width=49 style='border-top:none;border-left:none;
  width:37pt' colspan="2">　</td>


            <%----------------- 日期-----------------%>
            <td class=xl3332533 width=121 style='border-top:none;width:91pt'><span
                    style='mso-spacerun:yes'>&nbsp;</span>
                <FONT SIZE="1">
                    <%=1%>月
                    <%=2%>日
                    <%=3%>~
                    <%=4%>月
                    <%=5%>日
                </FONT>
            </td>

            <%----------------- 加班时数-----------------%>
            <td class=xl3432533 width=25 style='width:19pt'>8</td>
            <%----------------- 加班类型（平时）-----------------%>
            <td class=xl3032533 width=25 style='border-top:none;border-left:none;
  width:19pt'>　</td>
            <%----------------- 加班类型（休息日）-----------------%>
            <td class=xl3032533 width=25 style='border-top:none;border-left:none;
  width:19pt'></td>

            <%----------------- 加班类型（法定节假日）-----------------%>
            <td class=xl3832533 style='border-top:none;border-left:none'>√　</td>
            <td class=xl4832533 width=68 style='border-top:none;border-left:none;
  width:51pt'>　</td>
            <td class=xl3832533 style='border-top:none;border-left:none'>　</td>
            <td class=xl3832533 style='border-top:none;border-left:none'>　</td>
        </tr>

        <%
            //------------------------------------以下程序是各表的临时加班内容---------------------
        %>



        <tr height=37 style='mso-height-source:userset;height:27.95pt'>

            <%//----------------- 序号-----------------%>
            <td height=37 class=xl2932533 style='height:27.95pt;border-top:none' x:num><%=33%></td>

            <%//----------------- 员工编号-----------------%>
            <td class=xl4332533 width=61 style='border-top:none;border-left:none;
  width:46pt'>


                <font size='2'>
                </font>

            </td>

            <%//-----------------姓名-----------------%>
            <td class=xl4932533 width=72 style='border-top:none;border-left:none;
  width:54pt'>



                <img width=65 height=35 src="images/dd.GIF" >
            </td>

            <%//-----------------科-----------------%>
            <td class=xl2932533 style='border-top:none;border-left:none'><%=menber_department%></td>

            <%//-----------------日期-----------------%>
            <td class=xl3332533 width=123 style='border-top:none;width:92pt'>
                <font size='1'>
                    <%=1%>月
                    <%=2%>日
                    <%=3%>~
                    <%=4%>月
                    <%=5%>日
                    <%=6%>
                </FONT>
            </td>

            <%//-----------------加班时数-----------------%>
            <td class=xl3432533 width=25 style='width:19pt'><%=2222222%> </td>

            <%//-----------------加班类型（平时）-----------------%>
            <td class=xl3032533 width=25 style='border-top:none;border-left:none;
  width:19pt'>√</td>

            <%//-----------------加班类型（休息日）-----------------%>
            <td class=xl3032533 width=25 style='border-top:none;border-left:none;
  width:19pt'></td>

            <%//-----------------加班类型（法定节假日）-----------------%>
            <td class=xl3032533 width=37 style='border-top:none;border-left:none;
  width:28pt'>　</td>

            <%//-----------------加班原因-----------------%>
            <td class=xl3532533 width=132 style='border-top:none;border-left:none;
  width:99pt'>　






            </td>

            <%//-----------------可负责人同意-----------------%>
            <td class=xl4832533 width=49 style='border-top:none;border-left:none;
  width:37pt' colspan="2">　</td>


            <td class=xl3332533 width=121 style='border-top:none;width:91pt'><span style='mso-spacerun:yes'>&nbsp;</span>
                <font size='1'>
                    <%=1%>月
                    <%=2%>日
                    <%=3%>~
                    <%=4%>月
                    <%=5%>日
                    <%=6%>

                </FONT>
            </td>

            <%//-----------------加班时数-----------------%>
            <td class=xl3432533 width=25 style='width:19pt'><%=3%>

            </td>

            <%//-----------------加班类型（平时）-----------------%>
            <td class=xl3032533 width=25 style='border-top:none;border-left:none;
  width:19pt'>　√</td>

            <%//-----------------加班类型（休息日）-----------------%>
            <td class=xl3032533 width=25 style='border-top:none;border-left:none;
  width:19pt'>　</td>

            <%//-----------------加班类型（法定节假日）-----------------%>
            <td class=xl3832533 style='border-top:none;border-left:none'>　</td>

            <%//-----------------科负责人核准-----------------%>
            <td class=xl4832533 width=68 style='border-top:none;border-left:none;
  width:51pt'>　</td>

            <%//-----------------加班费-----------------%>
            <td class=xl3832533 style='border-top:none;border-left:none'>　</td>

            <%//-----------------补休-----------------%>
            <td class=xl3832533 style='border-top:none;border-left:none'>　</td>
        </tr>




        <%
            //------------------------------------以下程序是各表的空格补充内容加班内容---------------------
        %>

        <tr height=37 style='mso-height-source:userset;height:27.95pt'>
            <td height=37 class=xl2932533 style='height:27.95pt;border-top:none' x:num><%=44%></td>
            <td class=xl4332533 width=61 style='border-top:none;border-left:none;
  width:46pt'>　</td>
            <td class=xl4932533 width=72 style='border-top:none;border-left:none;
  width:54pt'>　</td>
            <td class=xl2932533 style='border-top:none;border-left:none'><%=menber_department%></td>
            <td class=xl3332533 width=123 style='border-top:none;width:92pt'><span
                    style='mso-spacerun:yes'>&nbsp;</span>月 日 时 分~ 月 日 时 分</td>
            <td class=xl3432533 width=25 style='width:19pt'>　</td>
            <td class=xl3032533 width=25 style='border-top:none;border-left:none;
  width:19pt'>　</td>
            <td class=xl3032533 width=25 style='border-top:none;border-left:none;
  width:19pt'>　</td>
            <td class=xl3032533 width=37 style='border-top:none;border-left:none;
  width:28pt'>　</td>
            <td class=xl3532533 width=132 style='border-top:none;border-left:none;
  width:99pt'>　</td>
            <td class=xl4832533 width=49 style='border-top:none;border-left:none;
  width:37pt' colspan="2">　</td>

            <td class=xl3332533 width=121 style='border-top:none;width:91pt'><span
                    style='mso-spacerun:yes'>&nbsp;</span>月 日 时 分~ 月 日 时 分</td>
            <td class=xl3432533 width=25 style='width:19pt'>　</td>
            <td class=xl3032533 width=25 style='border-top:none;border-left:none;
  width:19pt'>　</td>
            <td class=xl3032533 width=25 style='border-top:none;border-left:none;
  width:19pt'>　</td>
            <td class=xl3832533 style='border-top:none;border-left:none'>　</td>
            <td class=xl4832533 width=68 style='border-top:none;border-left:none;
  width:51pt'>　</td>
            <td class=xl3832533 style='border-top:none;border-left:none'>　</td>
            <td class=xl3832533 style='border-top:none;border-left:none'>　</td>
        </tr>


        <%
            //------------------------------------以下程序是各表的固定内容---------------------
        %>


        <tr height=32 style='mso-height-source:userset;height:24.0pt'>
            <td colspan=4 height=32 class=xl6232533 style='border-right:1.5pt solid black;
  height:24.0pt'>合计</td>
            <td class=xl4132533 width=123 style='border-top:none;border-left:none;
  width:92pt'>　</td>
            <td class=xl4032533 style='border-top:none'>

                <%=totalRec_houre%>　



            </td>
            <td class=xl3832533 style='border-top:none;border-left:none'>　</td>
            <td class=xl3832533 style='border-top:none;border-left:none'>　</td>
            <td class=xl3832533 style='border-top:none;border-left:none'>　</td>
            <td class=xl3832533 style='border-top:none;border-left:none'>　</td>
            <td class=xl3732533 style='border-top:none;border-left:none' colspan="2">　</td>
            <td class=xl4132533 width=121 style='border-top:none;border-left:none;
  width:91pt'>　</td>
            <td class=xl3832533 style='border-top:none;border-left:none'>　</td>
            <td class=xl3832533 style='border-top:none;border-left:none'>　</td>
            <td class=xl3832533 style='border-top:none;border-left:none'>　</td>
            <td class=xl3832533 style='border-top:none;border-left:none'>　</td>
            <td class=xl3832533 style='border-top:none;border-left:none'>　</td>
            <td class=xl3832533 style='border-top:none;border-left:none'>　</td>
            <td class=xl3832533 style='border-top:none;border-left:none'>　</td>
        </tr>
        <tr height=37 style='mso-height-source:userset;height:27.75pt'>
            <td height=37 class=xl1532533 style='height:27.75pt'></td>
            <td class=xl1532533>考勤员<span style='display:none'>：</span></td>
            <td colspan=2 class=xl6432533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533>部门负责人：</td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
        </tr>
        <tr height=28 style='mso-height-source:userset;height:21.0pt'>
            <td colspan=9 height=28 class=xl6132533 style='height:21.0pt'>说明：本表格仅用于月度加班计划中已申报的加班情况</td>
            <td class=xl4232533></td>
            <td class=xl4232533></td>
            <td class=xl4232533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
        </tr>
        <tr height=19 style='height:14.25pt'>
            <td height=19 style='height:14.25pt' align=left valign=top>


            </td>
            <td class=xl2432533></td>
            <td class=xl2432533></td>
            <td class=xl2432533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
            <td class=xl1532533></td>
        </tr>
    </table>





</div>


</body>

</html>
