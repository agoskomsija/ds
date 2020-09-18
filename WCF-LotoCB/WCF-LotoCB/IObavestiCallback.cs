using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.Text;
using System.Threading.Tasks;

namespace WCF_LotoCB
{
    public interface IObavestiCallback
    {
        [OperationContract(IsOneWay = true)]
        void NoviBroj(int broj);
        [OperationContract(IsOneWay = true)]
        void ObavestiPobednika(string nadminak,int brojIzvucenihBrojeva);
    }
}
