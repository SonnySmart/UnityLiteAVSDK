
#if UNITY_IPHONE || UNITY_STANDALONE_OSX
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEditor.Callbacks;
using UnityEditor;
using UnityEditor.iOS.Xcode;
using System.IO;

namespace liteva
{
	public class OnBuild : MonoBehaviour
	{
		[PostProcessBuild]
		public static void OnPostprocessBuild(BuildTarget target, string path)
		{
			if (target != BuildTarget.iOS)
				return;

			PBXPorject (path);

			InfoProject (path);
		}

		//pbxproj
		static void PBXPorject(string path)
		{
			// 初始化
			var projectPath = PBXProject.GetPBXProjectPath (path);
			PBXProject pbxProject = new PBXProject();
			pbxProject.ReadFromFile(projectPath);

			string targetGuid = pbxProject.TargetGuidByName("Unity-iPhone");

			// 添加flag
			pbxProject.AddBuildProperty(targetGuid, "OTHER_LDFLAGS", "-ObjC");
			// 关闭Bitcode
			pbxProject.SetBuildProperty(targetGuid, "ENABLE_BITCODE", "NO");

			// 添加framwrok
			pbxProject.AddFrameworkToProject(targetGuid, "Accelerate.framework", false);

			// 添加自定义framework
			//string targetFramework = "Frameworks/TXLiteAVSDK_Smart.framework";
			//string resFramework = "Assets/Libs/TXLiteAVSDK_Smart.framework";
			//CopyAndReplaceDirectory(resFramework, Path.Combine(path, targetFramework));
			//pbxProject.AddFileToBuild(targetGuid, pbxProject.AddFile(targetFramework, targetFramework, PBXSourceTree.Source));

			//添加lib
			AddLibToProject(pbxProject, targetGuid, "libsqlite3.tbd");
			AddLibToProject(pbxProject, targetGuid, "libc++.tbd");
			AddLibToProject(pbxProject, targetGuid, "libz.tbd");
			AddLibToProject(pbxProject, targetGuid, "libresolv.tbd");

			//Header Search Paths 头文件搜索路径
			//$(SRCROOT)/Frameworks/TXLiteAVSDK_Smart.framework/Headers
			//Header Search Paths (HEADER_SEARCH_PATHS) 是否设置正确。在Search Paths group下。
			//Framework Search Paths (FRAMEWORK_SEARCH_PATHS) 是否设置正确。在Search Paths group下。
			//Library Search Paths (LIBRARY_SEARCH_PATHS) 是否设置正确。在Search Paths group下。
			pbxProject.AddBuildProperty(targetGuid, "HEADER_SEARCH_PATHS", "$(SRCROOT)/Frameworks/Libs/TXLiteAVSDK_Smart.framework/Headers");
			pbxProject.AddBuildProperty(targetGuid, "FRAMEWORK_SEARCH_PATHS", "$(SRCROOT)/Frameworks");

			// 应用修改
			File.WriteAllText(projectPath, pbxProject.WriteToString());
		}

		//info.plist
		static void InfoProject(string path)
		{
			// 修改Info.plist文件
			var plistPath = Path.Combine(path, "Info.plist");
			var plist = new PlistDocument();
			plist.ReadFromFile(plistPath);

			//相机权限：
			plist.root.SetString ("Privacy - Camera Usage Description", "相机");
			plist.root.SetString ("Privacy - Microphone Usage Description", "麦克风");

			// 应用修改
			plist.WriteToFile(plistPath);
		}

		//添加lib方法
		static void AddLibToProject(PBXProject inst, string targetGuid, string lib)
		{
			string fileGuid = inst.AddFile("usr/lib/" + lib, "Frameworks/" + lib, PBXSourceTree.Sdk);
			inst.AddFileToBuild(targetGuid, fileGuid);
		}

		// ちょっとしたユーティリティ関数（http://goo.gl/fzYig8を参考）
		internal static void CopyAndReplaceDirectory(string srcPath, string dstPath)
		{
			if (Directory.Exists(dstPath))
				Directory.Delete(dstPath);
			if (File.Exists(dstPath))
				File.Delete(dstPath);

			FileUtil.CopyFileOrDirectory (srcPath, dstPath);
		}
	}
}
#endif
