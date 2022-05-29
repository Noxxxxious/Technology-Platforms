using System;
using System.IO;

namespace lab1_csharp
{
    class FileInfoExt : DataExt
    {
		public FileInfoExt(FileInfo fileInfo)
		{
			this.info = fileInfo;
		}

		protected override string Format(int recursionDepth)
		{
			string format = "";
			for (int i = 0; i < recursionDepth; ++i)
				format += '\t';
			format += String.Format("{0} {1} bytes {2}", info.Name, ((FileInfo)info).Length, info.GetDOSAttributes());
			return format;
		}

		protected internal override void Print(int recursionDepth)
		{
			Console.WriteLine(this.Format(recursionDepth));
		}
	}
}
