using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace WCF_DolazakNaPosao
{
    [ServiceContract(SessionMode = SessionMode.Allowed)]
    public interface IPrijava
    {
        [OperationContract]
        bool PrijaviSe(string ime);
        [OperationContract]
        bool OdjaviSe(string ime);
        [OperationContract]
        List<string> VratiProvedenoVreme(string ime);
        [OperationContract]
        List<string> VratiSve();
    }

    [DataContract]
    public class Log
    {
        [DataMember]
        public DateTime vreme;
        [DataMember]
        public bool uspesno;
        public Log(bool uspesno)
        {
            vreme = DateTime.Now;
            this.uspesno = uspesno;
        }
    }


    
}
