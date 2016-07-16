<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
	<xsl:output method="xml" indent="yes" />
	<xsl:template match="/">
		<fo:root>
			<fo:layout-master-set>
				<fo:simple-page-master 
					master-name="A4-portrait"
					page-height="29.7cm" 
					page-width="21.0cm" 
					margin="1cm">
					<fo:region-body />
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="A4-portrait">
				<fo:flow flow-name="xsl-region-body">
					<fo:block font-size="80%" font-weight="bold" text-align="center">Public Works Department</fo:block>
					<fo:block font-size="80%" font-weight="bold" text-align="center">Street and Traffic Division
					</fo:block>
					<fo:block font-size="80%" font-weight="bold" text-align="center">5th Floor, City Hall</fo:block>
					<fo:block font-size="80%" font-weight="bold" text-align="center">414 East 12th Street</fo:block>
					<fo:block font-size="80%" font-weight="bold" text-align="center">Kansas City, Missouri 64106-2705
					</fo:block>
					<fo:block>&#xA0;</fo:block>
					<fo:block text-align="center" text-decoration="underline" font-weight="bold">APPLICATION
						FOR EXCAVATION PERMIT
					</fo:block>
					<fo:block>&#xA0;</fo:block>

					<fo:table>
						<fo:table-column column-width="120mm" />
						<fo:table-column column-width="100mm" />

						<fo:table-body font-size="80%" >
							<fo:table-row height="20pt">
								<fo:table-cell>
									<fo:block >APPLICANT Name: <xsl:value-of select="excavation/applicantName"/></fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block>EMAIL:<xsl:value-of select="excavation/applicantEmail"/></fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row height="20pt">
								<fo:table-cell>
									<fo:block>FIRM RESPONSIBLE FOR EXCAVATION: <xsl:value-of select="excavation/responsibleFirm"/></fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row height="20pt">
								<fo:table-cell>
									<fo:block>BUSINESS ADDRESS:<xsl:value-of select="excavation/businessAddress"/></fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block>PHONE:<xsl:value-of select="excavation/phone"/></fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row height="20pt">
								<fo:table-cell>
									<fo:block>CITY/STATE/ZIP: <xsl:value-of select="excavation/cityStateZip"/></fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block>FAX: <xsl:value-of select="excavation/fax"/></fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row height="20pt">
								<fo:table-cell>
									<fo:block>JOB SUPERINTENDENT NAME: <xsl:value-of select="excavation/superintendent"/></fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block>CONTACT PHONE: <xsl:value-of select="excavation/contactPhone"/></fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>

					</fo:table>
					
					<fo:block>&#xA0;</fo:block>
					<fo:block text-align="center" text-decoration="underline" font-weight="bold">LOCATION OF EXCAVATION
					</fo:block>
					<fo:block>&#xA0;</fo:block>
					<fo:block font-size="80%" font-weight="bold">STREET ADDRESS NUMBER: <xsl:value-of select="excavation/jobAddress"/></fo:block>
					<fo:block font-size="80%">SEE ATTACHMENT FOR SKETCH OF THE WORK AREA OR COPY OF THE PLANS</fo:block>
					<fo:block>&#xA0;</fo:block>
					<fo:block>&#xA0;</fo:block>
					
					<fo:block font-size="80%">DESCRIPTION OF WORK: <xsl:value-of select="excavation/workDescription"/></fo:block>

					<fo:block>&#xA0;</fo:block>
					<fo:block text-align="center" text-decoration="underline" font-weight="bold">SIZE OF DIG
					[NUMBER OF HOLES, LENGTH, WIDTH AND DEPTH]
					</fo:block>
					<fo:block font-size="80%"><xsl:value-of select="excavation/sizeDescription"/></fo:block>
					<fo:block font-size="80%">BORE:<xsl:value-of select="excavation/bore"/></fo:block>
					<fo:block font-size="80%">Will any portion of the excavation be in street pavement? <xsl:value-of select="excavation/streetPavementExcavation"/></fo:block>
					<fo:block font-size="80%">Length and width of pavement cut:  </fo:block>
					<fo:block font-size="80%">Anticipated schedule of work    Start Date:     End Date:  </fo:block>
					<fo:block>&#xA0;</fo:block>
					<fo:block>&#xA0;</fo:block>
					<fo:block font-size="80%">I certify that I have read, have understood and will comply
					with the requirements of the City of Kansas City SR-1 Standards for completions.</fo:block>
					<fo:block>&#xA0;</fo:block>
					<fo:block font-size="80%">APPLICANT'S SIGNATURE: <fo:leader leader-pattern="rule" leader-length="75mm"/>
					<fo:leader leader-pattern="space" leader-length="5mm"/>DATE: <fo:leader leader-pattern="rule" leader-length="25mm"/>
					</fo:block>					
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
</xsl:stylesheet>