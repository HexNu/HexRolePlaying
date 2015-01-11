<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : campaign-book.xsl
    Created on : den 9 januari 2015, 19:43
    Author     : hln
    Description: Stylesheet for presenting campaigns in .tex format
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="text"/>
    <xsl:preserve-space elements="*"/>
        

    <xsl:template match="/cfx">
        <xsl:text>\documentclass[pdftex,a4paper]{book}&#10;</xsl:text>
        <xsl:text>&#10;</xsl:text>
        <xsl:text>%Imports&#10;</xsl:text>
        <xsl:text>\usepackage{multicol,color,titlesec,marginnote,framed}&#10;</xsl:text>
        <xsl:text>\usepackage[usenames,dvipsnames]{xcolor}&#10;</xsl:text>
        <xsl:text>\usepackage[utf8]{inputenc}&#10;</xsl:text>
        <xsl:text>\usepackage[pdftex]{graphicx}&#10;</xsl:text>
        <xsl:text>&#10;</xsl:text>
        <xsl:text>%Color Definitions&#10;</xsl:text>
        <xsl:text>\definecolor{referee-light}{HTML}{EEEEEE}&#10;</xsl:text>
        <xsl:text>\definecolor{referee-dark}{HTML}{443355}&#10;</xsl:text>
        <xsl:text>&#10;</xsl:text>
        <xsl:text>%New Commands&#10;</xsl:text>
        <xsl:text>\newcommand{\HRule}{\rule{\linewidth}{0.5mm}}&#10;</xsl:text>
        <xsl:text>\newcommand{\RefereeOnly}[1]{\color{referee-dark}\textit{{#1}}}&#10;</xsl:text>
        <xsl:text>\newcommand{\RefereeNotes}[1]{\marginnote{\RefereeOnly{{#1}}}[0.5cm]}&#10;</xsl:text>
        <xsl:text>\newcommand{\RefereeInfo}[1]{&#10;</xsl:text>
        <xsl:text>    ~\\[0.5cm]&#10;</xsl:text>
        <xsl:text>    \noindent&#10;</xsl:text>
        <xsl:text>    \fcolorbox{referee-dark}{referee-light}{&#10;</xsl:text>
        <xsl:text>        \parbox[b][][t]{0.98\textwidth}{&#10;</xsl:text>
        <xsl:text>            \RefereeOnly{\textsc{Referee Only}\\{#1}}&#10;</xsl:text>
        <xsl:text>        }&#10;</xsl:text>
        <xsl:text>    }&#10;</xsl:text>
        <xsl:text>}&#10;</xsl:text>
        <xsl:text>&#10;</xsl:text>
        <xsl:text>%Style Instructions&#10;</xsl:text>
        <xsl:text>\marginparsep = 0.5cm&#10;</xsl:text>
        <xsl:text>\titlespacing*{\chapter}{60pt}{170pt}{48pt}&#10;</xsl:text>
        <xsl:text>\titleformat{\chapter}[display]{\normalfont\huge\bfseries}{\chaptertitlename\ \thechapter}{5pt}{\Huge}&#10;</xsl:text>
        <xsl:text>&#10;</xsl:text>
        <xsl:text>%Document starts&#10;</xsl:text>
        <xsl:text>\begin{document}&#10;</xsl:text>
        <xsl:text>%Front Matter&#10;</xsl:text>
        <xsl:text>\frontmatter&#10;</xsl:text>
        <xsl:text>%Title Page&#10;</xsl:text>
        <xsl:text>\begin{titlepage}&#10;</xsl:text>
        <xsl:text>\begin{center}&#10;</xsl:text>
        <xsl:text>\includegraphics[width=0.15\textwidth]{Images/</xsl:text>
        <xsl:value-of select="translate(campaign/campaign-type/@label,' ','_')"/>
        <xsl:text>_Logo}~\\[1.5cm]&#10;</xsl:text>
        <xsl:text>\textsc{\LARGE </xsl:text>
        <xsl:value-of select="campaign/campaign-type"/>
        <xsl:text>}\\[1.5cm]&#10;</xsl:text>
        <xsl:text>\textsc{\large A </xsl:text>
        <xsl:value-of select="campaign/campaign-type/@label"/>
        <xsl:text> Campaign}\\[0.5cm]</xsl:text>
        <xsl:text>&#10;</xsl:text>
        <xsl:text>\HRule \\[0.4cm]&#10;</xsl:text>
        <xsl:text>{ \huge \bfseries </xsl:text>
        <xsl:value-of select="campaign/title"/>
        <xsl:text> \\[0.4cm] }&#10;</xsl:text>
        <xsl:text>\HRule&#10;</xsl:text>
        <xsl:text>\vfill&#10;</xsl:text>
        <xsl:text>{\large \today} \\[1cm]&#10;</xsl:text>
        <xsl:text>\textsc{© 2015 -- Hex Publishing}&#10;</xsl:text>
        <xsl:text>&#10;</xsl:text>
        <xsl:text>\end{center}&#10;</xsl:text>
        <xsl:text>\end{titlepage}&#10;</xsl:text>
        <xsl:text>&#10;</xsl:text>
        <xsl:text>\tableofcontents&#10;</xsl:text>
        <xsl:text>\listoffigures&#10;</xsl:text>
        <xsl:text>\chapter*{Introduction}&#10;</xsl:text>
        <xsl:text>\markboth{\MakeUppercase{Introduction}}{}&#10;</xsl:text>
        <xsl:text>\addcontentsline{toc}{chapter}{Introduction}&#10;</xsl:text>
        <xsl:value-of select="campaign/short-description"/>
        <xsl:text>&#10;</xsl:text>
        <xsl:text>\begin{description}&#10;</xsl:text>
        <xsl:for-each select="campaign/stories/story">
            <xsl:text>\item[</xsl:text>
            <xsl:value-of select="title"/>
            <xsl:text>] </xsl:text>
            <xsl:value-of select="short-description"/>
            <xsl:text>&#10;</xsl:text>
        </xsl:for-each>
        <xsl:text>\end{description}&#10;</xsl:text>
        <xsl:value-of select="campaign/description"/>
        <xsl:for-each select="campaign/supplements/supplement">
            <xsl:sort select="@file-path"/>
            <xsl:apply-templates select="." mode="in-content"/>
        </xsl:for-each>
        <xsl:text>&#10;</xsl:text>
        <xsl:text>\mainmatter&#10;</xsl:text>
        <xsl:text>\cleardoublepage&#10;</xsl:text>
        <xsl:text>&#10;</xsl:text>
        <xsl:text>%Stories&#10;</xsl:text>
        <xsl:apply-templates select="campaign/stories/story"/>
        <xsl:text>&#10;</xsl:text>
        <xsl:text>\part{\sc Appendices}&#10;</xsl:text>
        <xsl:text>\appendix&#10;</xsl:text>
        <xsl:text>\chapter{Figures with description}&#10;</xsl:text>
        <xsl:text>\cleardoublepage&#10;</xsl:text>
        <xsl:apply-templates select="//supplement">
            <xsl:sort select="@file-path"/>
        </xsl:apply-templates>
        <xsl:text>&#10;</xsl:text>
        <xsl:text>\backmatter&#10;</xsl:text>
        <xsl:text>\chapter{Concluding Words}&#10;</xsl:text>
        <xsl:text>That was the end of the World News. Good Night!&#10;</xsl:text>
        <xsl:text>&#10;</xsl:text>
        <xsl:text>\end{document}&#10;</xsl:text>
        <xsl:text>&#10;</xsl:text>
    </xsl:template>
    
    <xsl:template match="story">
        <xsl:text>\part{\sc </xsl:text>
        <xsl:value-of select="title"/>
        <xsl:text>}&#10;</xsl:text>
        <xsl:if test="referee-notes != ''">
            <xsl:text>\RefereeNotes{</xsl:text>
            <xsl:value-of select="referee-notes"/>
            <xsl:text>}&#10;</xsl:text>
        </xsl:if>
        <xsl:text>\textit{</xsl:text>
        <xsl:value-of select="description"/>
        <xsl:text>}&#10;</xsl:text>
        <xsl:for-each select="supplements/supplement">
            <xsl:sort select="@file-path"/>
            <xsl:apply-templates select="." mode="in-content"/>
        </xsl:for-each>
        <xsl:if test="referee-info != ''">
            <xsl:text>\RefereeInfo{</xsl:text>
            <xsl:value-of select="referee-info"/>
            <xsl:text>}&#10;</xsl:text>
        </xsl:if>
        <xsl:text>%Episodes&#10;</xsl:text>
        <xsl:apply-templates select="episodes/episode"/>
    </xsl:template>
    
    <xsl:template match="episode">
        <xsl:text>\chapter{\large </xsl:text>
        <xsl:value-of select="title"/>
        <xsl:text>}&#10;</xsl:text>
        <xsl:text>\textit{</xsl:text>
        <xsl:value-of select="description"/>
        <xsl:text>}&#10;\\[0.3cm]&#10;</xsl:text>
        <xsl:if test="referee-notes">
            <xsl:text>\RefereeNotes{</xsl:text>
            <xsl:value-of select="referee-notes"/>
            <xsl:text>}&#10;</xsl:text>
        </xsl:if>
        <xsl:value-of select="content"/>
        <xsl:text>&#10;</xsl:text>
        <xsl:for-each select="supplements/supplement">
            <xsl:sort select="file-path"/>
            <xsl:apply-templates select="." mode="in-content"/>
        </xsl:for-each>
        <xsl:text>&#10;</xsl:text>
        <xsl:if test="referee-info != ''">
            <xsl:text>\RefereeInfo{</xsl:text>
            <xsl:value-of select="referee-info"/>
            <xsl:text>}&#10;</xsl:text>
        </xsl:if>
    </xsl:template>
    
    <xsl:template match="supplement" mode="in-content">
        <xsl:choose>
            <xsl:when test="contains(@media-type, 'image')">
                <xsl:text>\begin{figure}[p]&#10;</xsl:text>
                <xsl:text>\centering&#10;</xsl:text>
                <xsl:text>\includegraphics[width=\linewidth]{</xsl:text>
                <xsl:value-of select="@file-path"/>
                <xsl:text>}&#10;</xsl:text>
                <xsl:text>\caption[</xsl:text>
                <xsl:value-of select="title"/>
                <xsl:text>]{</xsl:text>
                <xsl:value-of select="title"/>
                <xsl:text> -- \textit{</xsl:text>
                <xsl:value-of select="short-description"/>
                <xsl:text>}}&#10;</xsl:text>
                <xsl:text>\end{figure}</xsl:text>
            </xsl:when>
        </xsl:choose>
    </xsl:template>
    
    <xsl:template match="supplement">
        <xsl:choose>
            <xsl:when test="contains(@media-type, 'image')">
                <xsl:text>&#10;\section{</xsl:text>
                <xsl:text>\sc </xsl:text>
                <xsl:value-of select="title"/>
                <xsl:text>}&#10;</xsl:text>
                <xsl:text>\includegraphics[width=\linewidth]{</xsl:text>
                <xsl:value-of select="@file-path"/>
                <xsl:text>}&#10;</xsl:text>
                <xsl:if test="contains(description, '\\')">
                    <xsl:text>\clearpage&#10;</xsl:text>
                </xsl:if>
                <xsl:text>\noindent&#10;</xsl:text>
                <xsl:text>\textit{</xsl:text>
                <xsl:value-of select="description"/>
                <xsl:text>}&#10;</xsl:text>
            </xsl:when>
        </xsl:choose>
    </xsl:template>

</xsl:stylesheet>
