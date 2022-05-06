<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/">
    <html>
      <body>
        <h1>Test Case Results</h1>
        <table border="1">
          <tr>
            <th>stepno</th>
            <th>stepname</th>
            <th>status</th>
          </tr>
          <xsl:for-each select="urlset/url">
            <tr>
              <td>
                <xsl:value-of select="stepno" />
              </td>
              <td>
                <xsl:value-of select="stepname" />
              </td>
              <td>
                <xsl:value-of select="status" />
              </td>
            </tr>
          </xsl:for-each>
        </table>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>