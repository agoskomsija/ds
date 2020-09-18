using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Runtime.Remoting.Proxies;
using System.ServiceModel;
using System.Text;
using System.Threading.Tasks;
using Klijent.LotoServiceReference;
using WCF_LotoCB;

namespace Klijent
{
    public class Program
    {

        //class PrintKlijent
        //{
        //    public void PrintIt(string text)
        //    {
        //        Console.WriteLine(text);
        //    }
        //}

        static void Main(string[] args)
        {
            //PrintKlijent k = new PrintKlijent();
            string nadimak = Console.ReadLine();
            ObavestiCallback callback = new ObavestiCallback(nadimak/*,k.PrintIt*/);
            InstanceContext ic = new InstanceContext(callback);
            LotoClient proxy = new LotoClient(ic);
            List<int> kombBrojevi = new List<int>()
            {
                1,2,3,4,5,6,7
            };
            
            Kombinacija kombinacija = new Kombinacija(kombBrojevi);
            
            proxy.DodajKombinaciju(nadimak, kombinacija);
            int x = Convert.ToInt32(Console.ReadLine());
            if(x == 1)
                proxy.ZapocniIzvlacenje();

            Console.ReadLine();
        }

       
    }
}
