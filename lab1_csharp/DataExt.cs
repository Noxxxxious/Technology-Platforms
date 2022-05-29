using System.IO;

namespace lab1_csharp
{
    public abstract class DataExt
    {
        protected FileSystemInfo info;
        protected abstract string Format(int recursionDepth);
        protected internal abstract void Print(int recursionDepth);
    }
}
