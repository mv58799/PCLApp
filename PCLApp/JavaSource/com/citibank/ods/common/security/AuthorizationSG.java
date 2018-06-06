//
//©2002-2007 Accenture. All rights reserved. 
//
/**
* Classe responsável pela validação de funções do SecurityGateway
* 
* @see com.citibank.ods.common.security;
* @version 1.0
* @author marcelo.s.oliveira,June 1 , 2007
* 
*/

package com.citibank.ods.common.security;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.user.User;


public class AuthorizationSG extends Authorization
{

    public boolean hasAccess(User user_, String functionURI_)
        throws UnexpectedException
    {
        ArrayList userFunctions = user_.getFunctions();
        boolean hasAccess = false;
        if(userFunctions.contains(functionURI_)){
        	hasAccess = true;
        }else{
        	hasAccess = false;
        }
        return hasAccess;
    }
    
    public boolean hasNEWCPBAccess(User user, String functionID) throws UnexpectedException {
        
        boolean hasAccess = false;
        if(user!=null && !StringUtils.isBlank(functionID)){
        	
        	if(functionID.equalsIgnoreCase("/NEWCPB.Menu")){
        		
        		hasAccess = (user.getUserAccess().isHasAccessMenuNovoCPB());
        	
        	}else if(functionID.equalsIgnoreCase("/NEWCPB.Customer")){
        		
        		hasAccess = (user.getUserAccess().isHasAccessNovoCPBCli());
        	
        	}else if(functionID.equalsIgnoreCase("/NEWCPB.EG")){
        		
        		hasAccess = (user.getUserAccess().isHasAccessNovoCPBEG());
        		
        	}else if(functionID.equalsIgnoreCase("/NEWCPB.Account")){
        		
        		hasAccess = (user.getUserAccess().isHasAccessNovoCPBConta());
        		
        	}else if(functionID.equalsIgnoreCase("/NEWCPB.Banker")){
        		
        		hasAccess = (user.getUserAccess().isHasAccessNovoCPBBanker());
        		
        	}else if(functionID.equalsIgnoreCase("/NEWCPB.CentralApproval")){
        		
        		hasAccess = (user.getUserAccess().isHasAccessNovoCPBAprovacaoCentralizada());
        	
        	}else if(functionID.equalsIgnoreCase("/NEWCPB.RegisterDataCustomer")){
        		
        		hasAccess = user.getUserAccess().isHasAccessNovoCPBCli() && (user.getUserAccess().isHasAccessNovoCPBCliConsDadosCad());
        	}else if(functionID.equalsIgnoreCase("/NEWCPB.RegisterDataRisk")){
        		
        		hasAccess = user.getUserAccess().isHasAccessNovoCPBEG() && (user.getUserAccess().isHasAccessNovoCPBEGConsCadRisco() ||
        				user.getUserAccess().isHasAccessNovoCPBEGInsRisco() || 
        	    		user.getUserAccess().isHasAccessNovoCPBEGAtualRisco());
        		
        	}else if(functionID.equalsIgnoreCase("/NEWCPB.RegisterDataRiskSearch")){
        		
        		hasAccess = user.getUserAccess().isHasAccessNovoCPBEG() && (user.getUserAccess().isHasAccessNovoCPBEGConsCadRisco());
        		
        	}else if(functionID.equalsIgnoreCase("/NEWCPB.RegisterDataRiskInsert")){
        		
        		hasAccess = user.getUserAccess().isHasAccessNovoCPBEG() && user.getUserAccess().isHasAccessNovoCPBEGInsRisco();
        		
        	}else if(functionID.equalsIgnoreCase("/NEWCPB.AssociationAccountSearch")){
        		
        		hasAccess = user.getUserAccess().isHasAccessNovoCPBEG() && (user.getUserAccess().isHasAccessNovoCPBEGConsAssoConta());
        		
        	}else if(functionID.equalsIgnoreCase("/NEWCPB.AssociationAccount")){
        		
        		hasAccess = user.getUserAccess().isHasAccessNovoCPBEG() && (user.getUserAccess().isHasAccessNovoCPBEGConsAssoConta() ||
        				user.getUserAccess().isHasAccessNovoCPBEGInsAssoConta() || 
        	    		user.getUserAccess().isHasAccessNovoCPBEGAtualAssoConta());
        		
        	}else if(functionID.equalsIgnoreCase("/NEWCPB.AssociationAccountInsert")){
        		
        		hasAccess = user.getUserAccess().isHasAccessNovoCPBEG() && user.getUserAccess().isHasAccessNovoCPBEGInsAssoConta();
        		
        	}else if(functionID.equalsIgnoreCase("/NEWCPB.RegisterAuthorized")){
        		
        		hasAccess = user.getUserAccess().isHasAccessNovoCPBEG() && (user.getUserAccess().isHasAccessNovoCPBCliConsCadAut() ||
        	    		user.getUserAccess().isHasAccessNovoCPBCliAtualCadAut());
        		
        	}else if(functionID.equalsIgnoreCase("/NEWCPB.RegisterAuthorizedSearch")){
        		
        		hasAccess = user.getUserAccess().isHasAccessNovoCPBCli() && user.getUserAccess().isHasAccessNovoCPBCliConsCadAut();
        		
        	}else if(functionID.equalsIgnoreCase("/NEWCPB.RegisterAuthorizedInsert")){
        		
        		hasAccess = user.getUserAccess().isHasAccessNovoCPBCli() && user.getUserAccess().isHasAccessNovoCPBCliAtualCadAut();
        		
        	}else if(functionID.equalsIgnoreCase("/NEWCPB.AccountComplementData")){
        		
        		hasAccess = user.getUserAccess().isHasAccessNovoCPBConta() && (user.getUserAccess().isHasAccessNovoCPBContaConsDadosCompdaConta() ||
        	    		user.getUserAccess().isHasAccessNovoCPBContaAtualDadosCompdaConta());
        		
        	}else if(functionID.equalsIgnoreCase("/NEWCPB.AccountComplementDataSearch")){
        		
        		hasAccess = user.getUserAccess().isHasAccessNovoCPBConta() && (user.getUserAccess().isHasAccessNovoCPBContaConsDadosCompdaConta());
        		
        	}else if(functionID.equalsIgnoreCase("/NEWCPB.AccountComplementDataInsert")){
        		
        		hasAccess = user.getUserAccess().isHasAccessNovoCPBConta() && user.getUserAccess().isHasAccessNovoCPBContaAtualDadosCompdaConta();
        		
        	}else if(functionID.equalsIgnoreCase("/NEWCPB.StatusCpfCnpj")){
        		
        		hasAccess = user.getUserAccess().isHasAccessNovoCPBCli() && (user.getUserAccess().isHasAccessNovoCPBCliConsStatusCPFCNPJ()
        				 || user.getUserAccess().isHasAccessNovoCPBCliAtualrStatusCPFCNPJ() || user.getUserAccess().isHasAccessNovoCPBCliDelStatusCPFCNPJ());
        		
        	}else if(functionID.equalsIgnoreCase("/NEWCPB.StatusCpfCnpjSearch")){
        		
        		hasAccess = user.getUserAccess().isHasAccessNovoCPBCli() && user.getUserAccess().isHasAccessNovoCPBCliConsStatusCPFCNPJ();
        		
        	 }else if(functionID.equalsIgnoreCase("/NEWCPB.StatusCpfCnpjUpdate")){
        		
        		hasAccess = user.getUserAccess().isHasAccessNovoCPBCli() && user.getUserAccess().isHasAccessNovoCPBCliAtualrStatusCPFCNPJ();
        		
            }else if(functionID.equalsIgnoreCase("/NEWCPB.Documents")){
        		
        		hasAccess = user.getUserAccess().isHasAccessNovoCPBCli() && (user.getUserAccess().isHasAccessNovoCPBCliConsDocuments()
        				 || user.getUserAccess().isHasAccessNovoCPBCliAtualrDocuments()) || user.getUserAccess().isHasAccessNovoCPBCliDelDocuments();
        		
        	}else if(functionID.equalsIgnoreCase("/NEWCPB.DocumentsSearch")){
        		
        		hasAccess = user.getUserAccess().isHasAccessNovoCPBCli() && user.getUserAccess().isHasAccessNovoCPBCliConsDocuments();
        		
        	}else if(functionID.equalsIgnoreCase("/NEWCPB.DocumentsInsert")){
        		
        		hasAccess = user.getUserAccess().isHasAccessNovoCPBCli() && user.getUserAccess().isHasAccessNovoCPBCliInsDocuments();
        		

        	}else if(functionID.equalsIgnoreCase("/NEWCPB.AccountMigration")){
    		
    		   hasAccess = user.getUserAccess().isHasAccessNovoCPBConta() && (user.getUserAccess().isHasAccessNovoCPBContaConsContasMigradas()
    				   || user.getUserAccess().isHasAccessNovoCPBContaInsContasMigradas() || user.getUserAccess().isHasAccessNovoCPBContaDelContasMigradas());
    		
	    	}else if(functionID.equalsIgnoreCase("/NEWCPB.AccountMigrationSearch")){
	    		
	    		hasAccess = user.getUserAccess().isHasAccessNovoCPBConta() && user.getUserAccess().isHasAccessNovoCPBContaConsContasMigradas();
	    		
	    	}else if(functionID.equalsIgnoreCase("/NEWCPB.AccountMigrationInsert")){
	    		
	    		hasAccess = user.getUserAccess().isHasAccessNovoCPBConta() && user.getUserAccess().isHasAccessNovoCPBContaInsContasMigradas();
	    		
	    	}else if(functionID.equalsIgnoreCase("/NEWCPB.QuestionsKe")){
        		
	    		hasAccess = user.getUserAccess().isHasAccessNovoCPBConta() && (user.getUserAccess().isHasAccessNovoCPBQuestionsKeCons() 
	    				|| user.getUserAccess().isHasAccessNovoCPBQuestionsKeAtual());  
	    		
	    	}else if(functionID.equalsIgnoreCase("/NEWCPB.QuestionsKeAuthorized")){
        		
	    		hasAccess = user.getUserAccess().isHasAccessNovoCPBConta() && (user.getUserAccess().isHasAccessNovoCPBQuestionsKeCons() 
	    				|| user.getUserAccess().isHasAccessNovoCPBQuestionsKeAtual());   
	    	}
        	
        }else{
        	hasAccess = false;
        }
        return hasAccess;
    }
}
