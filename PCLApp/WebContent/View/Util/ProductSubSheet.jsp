
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>

	<script type="text/javascript">
	 	var id_menu_Product =
		[<ods:AccessGrantedTag m_SGFunction="/Product.ProductList.List.Show.Clear">[null, 'Produtos', './Product.ProductList.List.Show.Clear?currentSubSheet=Produtos' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null],_cmSplit,</ods:AccessGrantedTag>
		[null, 'Players', null, '_id24:frmMenu', null
			<ods:AccessGrantedTag m_SGFunction="/Player.PlayerList.List.Show.Clear">, [null, 'Player', './Player.PlayerList.List.Show.Clear?currentSubSheet=Players' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null],</ods:AccessGrantedTag>
			<ods:AccessGrantedTag m_SGFunction="/ProdPlayerRole.ProdPlayerRoleList.List.Show.Clear">, [null, 'Consulta de Player x Produtos', './ProdPlayerRole.ProdPlayerRoleList.List.Show.Clear?currentSubSheet=Players' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null]</ods:AccessGrantedTag>],_cmSplit,
		[null, 'Parâmetros', null, '_id24:frmMenu', null,
				[null, 'Agregador', null, '_id24:frmMenu', null
					<ods:AccessGrantedTag m_SGFunction="/AggrProdPrvt.AggrProdPrvtList.List.Show.Clear">, [null, 'Consultar', './AggrProdPrvt.AggrProdPrvtList.List.Show.Clear?currentSubSheet=Parâmetros' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null], </ods:AccessGrantedTag>
					<ods:AccessGrantedTag m_SGFunction="/AggrProdPrvt.AggrProdPrvtDetail.Insert.Show">, [null, 'Inserir', './AggrProdPrvt.AggrProdPrvtDetail.Insert.Show?currentSubSheet=Parâmetros' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null]</ods:AccessGrantedTag>], 
				[null, 'Família', null, '_id24:frmMenu', null
					<ods:AccessGrantedTag m_SGFunction="/ProductFamilyPrvt.ProductFamilyPrvtList.List.Show.Clear">, [null, 'Consultar', './ProductFamilyPrvt.ProductFamilyPrvtList.List.Show.Clear?currentSubSheet=Parâmetros' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null], </ods:AccessGrantedTag>
					<ods:AccessGrantedTag m_SGFunction="/ProductFamilyPrvt.ProductFamilyPrvtDetail.Insert.Show">, [null, 'Inserir', './ProductFamilyPrvt.ProductFamilyPrvtDetail.Insert.Show?currentSubSheet=Parâmetros' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null]</ods:AccessGrantedTag>], 
				[null, 'Qualificação', null, '_id24:frmMenu', null
					<ods:AccessGrantedTag m_SGFunction="/ProdQlfyPrvt.ProdQlfyPrvtList.List.Show.Clear">, [null, 'Consultar', './ProdQlfyPrvt.ProdQlfyPrvtList.List.Show.Clear?currentSubSheet=Parâmetros' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null], </ods:AccessGrantedTag>
					<ods:AccessGrantedTag m_SGFunction="/ProdQlfyPrvt.ProdQlfyPrvtDetail.Insert.Show">, [null, 'Inserir', './ProdQlfyPrvt.ProdQlfyPrvtDetail.Insert.Show?currentSubSheet=Parâmetros' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null]</ods:AccessGrantedTag>], 
				[null, 'Classe de Ativo', null, '_id24:frmMenu', null
					<ods:AccessGrantedTag m_SGFunction="/ProdAsset.ProdAssetList.List.Show.Clear">, [null, 'Consultar', './ProdAsset.ProdAssetList.List.Show.Clear?currentSubSheet=Parâmetros' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null], </ods:AccessGrantedTag>
					<ods:AccessGrantedTag m_SGFunction="/ProdAsset.ProdAssetDetail.Insert.Show">, [null, 'Inserir', './ProdAsset.ProdAssetDetail.Insert.Show?currentSubSheet=Parâmetros' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null]</ods:AccessGrantedTag>],
				[null, 'Sub Classe de Ativo', null, '_id24:frmMenu', null
					<ods:AccessGrantedTag m_SGFunction="/ProdSubAsset.ProdSubAssetList.List.Show.Clear">, [null, 'Consultar', './ProdSubAsset.ProdSubAssetList.List.Show.Clear?currentSubSheet=Parâmetros' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null], </ods:AccessGrantedTag>
					<ods:AccessGrantedTag m_SGFunction="/ProdSubAsset.ProdSubAssetDetail.Insert.Show">, [null, 'Inserir', './ProdSubAsset.ProdSubAssetDetail.Insert.Show?currentSubSheet=Parâmetros' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null]</ods:AccessGrantedTag>], 
				[null, 'Tipo de Ativo', null, '_id24:frmMenu', null
					<ods:AccessGrantedTag m_SGFunction="/ProdAssetType.ProdAssetTypeList.List.Show.Clear">, [null, 'Consultar', './ProdAssetType.ProdAssetTypeList.List.Show.Clear?currentSubSheet=Parâmetros' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null], </ods:AccessGrantedTag>
					<ods:AccessGrantedTag m_SGFunction="/ProdAssetType.ProdAssetTypeDetail.Insert.Show">, [null, 'Inserir', './ProdAssetType.ProdAssetTypeDetail.Insert.Show?currentSubSheet=Parâmetros' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null]</ods:AccessGrantedTag>], 
				[null, 'Sub-Família', null, '_id24:frmMenu', null
					<ods:AccessGrantedTag m_SGFunction="/ProdSubFamlPrvt.ProdSubFamlPrvtList.List.Show.Clear">, [null, 'Consultar', './ProdSubFamlPrvt.ProdSubFamlPrvtList.List.Show.Clear?currentSubSheet=Parâmetros' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null], </ods:AccessGrantedTag>
					<ods:AccessGrantedTag m_SGFunction="/ProdSubFamlPrvt.ProdSubFamlPrvtDetail.Insert.Show">, [null, 'Inserir', './ProdSubFamlPrvt.ProdSubFamlPrvtDetail.Insert.Show?currentSubSheet=Parâmetros' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null]</ods:AccessGrantedTag>]],_cmSplit,
		<ods:AccessGrantedTag m_SGFunction="/Broker.BrokerList.List.Show.Clear">[null, 'Corretoras', './Broker.BrokerList.List.Show.Clear?currentSubSheet=Corretoras' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null],_cmSplit,</ods:AccessGrantedTag>
		[null, 'Aprovação', null, '_id24:frmMenu', null
			<ods:AccessGrantedTag m_SGFunction="/CentrApproval.CentrApprovalMovementList.List.Execute">, [null, 'Aprovação Centralizada', './CentrApproval.CentrApprovalMovementList.List.Execute?currentSheet=ProductSheet' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null], </ods:AccessGrantedTag>,
		],
		_cmSplit,
		[null, 'Subscrição', null, '_id24:frmMenu', null
			<ods:AccessGrantedTag m_SGFunction="/FundSubscriptionImport.FundSubscriptionImportDetail.Insert.Show">, [null, 'Importação', './FundSubscriptionImport.FundSubscriptionImportDetail.Insert.Show?currentSheet=ProductSheet' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null], </ods:AccessGrantedTag>
			<ods:AccessGrantedTag m_SGFunction="/FundSubscription.FundSubscriptionList.List.Show">, [null, 'Consulta', './FundSubscription.FundSubscriptionList.List.Show?currentSheet=ProductSheet' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null], </ods:AccessGrantedTag>
			<ods:AccessGrantedTag m_SGFunction="/FundSubscription.FundSubscriptionFundCodeList.List.Show">, [null, 'Código do Fundo', './FundSubscription.FundSubscriptionFundCodeList.List.Show?currentSheet=ProductSheet' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null], </ods:AccessGrantedTag>,
		],
		_cmSplit,
		[null, 'Termo de Adesão', null, '_id24:frmMenu', null
			<ods:AccessGrantedTag m_SGFunction="/MembershipTerm.MembershipTermList.List.Show">, [null, 'Termo', './MembershipTerm.MembershipTermList.List.Show.Clear?currentSheet=ProductSheet' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null] </ods:AccessGrantedTag>
		]];
	</script><div id="id_menu_Product"></div>

	<script type="text/javascript">	cmDraw ('id_menu_Product', id_menu_Product, 'hbr', cmThemeOffice, 'ODS_ThemeOffice');</script>
