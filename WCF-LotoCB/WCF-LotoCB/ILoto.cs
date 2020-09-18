using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace WCF_LotoCB
{
    [ServiceContract(SessionMode = SessionMode.Allowed, CallbackContract = typeof(IObavestiCallback))]
    public interface ILoto
    {
        [OperationContract]
        bool DodajKombinaciju(string nadimak, Kombinacija kombinacija);
        [OperationContract]
        void ObrisiKombinaciju(string nadimak);

        [OperationContract(IsOneWay = true)]
        void ZapocniIzvlacenje();
    }

    [DataContract]
    public class Kombinacija
    {
        [DataMember]
        List<int> kombinacija;
        public Kombinacija(List<int> kombinacija)
        {
            this.kombinacija = kombinacija;
        }

        public int Uporedi(Kombinacija k)
        {
            int result = 0;
            foreach(int br in k.kombinacija)
            {
                result = this.kombinacija.Contains(br) ? result + 1 : result;
            }
            return result;
        }
    }
}
