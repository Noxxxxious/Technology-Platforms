using System;
using System.Collections.Generic;

namespace lab1_csharp
{
    [Serializable]
    class Comparer : IComparer<string>
    {
        public int Compare(string s1, string s2)
        {
            if (s1.Length < s2.Length)
                return 1;
            if (s1.Length > s2.Length)
                return -1;
            return s1.CompareTo(s2);
        }
    }
}
