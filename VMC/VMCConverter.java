package VMC;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class VMCConverter 
{
	private final String version = "LKS Virtual Machine Code Version 1.0";
	private static String[] notinstrutionTypes = new String[] {"#SUSPEND",
	                                                     "#EXECUTE_SEQUENCE",
	                                                     "#NOP",
	                                                     "#JE",
	                                                     "#JNE",
	                                                     "#IF_EQUAL",
	                                                     "#IF_NEQUAL",
	                                                     "#IF_LESS",
	                                                     "#IF_LEQUAL",
	                                                     "#IF_GREATER",
	                                                     "#IF_GEQUAL",
	                                                     "#IF_EQUAL_F",
	                                                     "#IF_NEQUAL_F",
	                                                     "#IF_LESS_F",
	                                                     "#IF_LEQUAL_F",
	                                                     "#IF_GREATER_F",
	                                                     "#IF_GEQUAL_F",
	                                                     "#ADD",
	                                                     "#SUB",
	                                                     "#ADD_F",
	                                                     "#SUB_F",
	                                                     "#SET_VALUE",
	                                                     "#SET_VALUE_F",
	                                                     "#位置設定",
	                                                     "#向き設定",
	                                                     "#スケール設定",
	                                                     "#位置取得",
	                                                     "#向き取得",
	                                                     "#スケール取得",
	                                                     "#モーション設定",
	                                                     "#モーションリセット",
	                                                     "#ランダム整数値取得",
	                                                     "#ランダム実数値取得",
	                                                     "#条件ビットフラグ設定",
	                                                     "#状態ビットフラグ設定",
	                                                     "#条件ビットフラグ取得",
	                                                     "#状態ビットフラグ取得",
	                                                     "#睡眠状態へ移行",
	                                                     "#睡眠状態を解除",
	                                                     "#オブジェクトID取得",
	                                                     "#出会った人IDの取得",
	                                                     "#出会った人チェックの判定パラメーター設定",
	                                                     "#外部行動トリガー割り込み禁止設定",
	                                                     "#王様呼び出し割り込み禁止フラグ設定",
	                                                     "#王様会話割り込み禁止フラグ設定",
	                                                     "#友好度設定",
	                                                     "#短期記憶設定",
	                                                     "#長期記憶設定",
	                                                     "#短期記憶パラメーター取得",
	                                                     "#長期記憶パラメーター取得",
	                                                     "#記憶パラメーター取得",
	                                                     "#オブジェクト同期要求",
	                                                     "#オブジェクト同期要求（外部トリガー禁止貫通）",
	                                                     "#オブジェクト同期終了",
	                                                     "#オブジェクト位置設定",
	                                                     "#オブジェクト向き設定",
	                                                     "#オブジェクト位置取得",
	                                                     "#オブジェクト向き取得",
	                                                     "#オブジェクトモーション設定",
	                                                     "#オブジェクトモーション再生速度変更",
	                                                     "#オブジェクトモーションリセット",
	                                                     "#オブジェクト表示設定",
	                                                     "#オブジェクトもちものパーツ表示設定",
	                                                     "#オブジェクト外部行動トリガー割り込み禁止設定",
	                                                     "#オブジェクト王様呼び出し割り込み禁止フラグ設定",
	                                                     "#オブジェクト王様会話割り込み禁止フラグ設定",
	                                                     "#オブジェクト友好度設定",
	                                                     "#オブジェクト友好度取得",
	                                                     "#オブジェクト短期記憶設定",
	                                                     "#オブジェクト長期記憶設定",
	                                                     "#オブジェクト短期記憶パラメーター取得",
	                                                     "#オブジェクト長期記憶パラメーター取得",
	                                                     "#オブジェクト記憶パラメーター取得",
	                                                     "#オブジェクト自宅配置ID取得",
	                                                     "#オブジェクトのビジュアルを破棄",
	                                                     "#LSフラグ設定",
	                                                     "#LSフラグチェック",
	                                                     "#ランダムで位置取得",
	                                                     "#NPC会話中心位置取得",
	                                                     "#NPC割り当て仕事場ポイント番号取得",
	                                                     "#仕事場ポイントリスト_仕事場ポイントカウンターから情報取得",
	                                                     "#仕事場ポイントリスト_NPC仕事場ポイントカウンターから情報取得",
	                                                     "#仕事場ポイントリスト_ランダムで情報取得",
	                                                     "#仕事場ポイントリスト_ポイント番号指定で情報取得",
	                                                     "#自宅配置ID取得",
	                                                     "#仕事場配置ID取得",
	                                                     "#配置IDの場所に建物が建っているかチェック",
	                                                     "#配置IDから建物入口情報取得",
	                                                     "#現在の時間帯チェック",
	                                                     "#シグナルを指定範囲のオブジェクトに送信",
	                                                     "#シグナルを指定範囲のオブジェクトに送信_人数指定",
	                                                     "#葬式参加者配置情報取得",
	                                                     "#葬式終了",
	                                                     "#運搬アイテム設定",
	                                                     "#運搬アイテム設定_座標OBJ",
	                                                     "#オブジェクトにエフェクト発生",
	                                                     "#オブジェクトにエフェクトループ発生",
	                                                     "#オブジェクトのエフェクト消去",
	                                                     "#オブジェクトHP設定",
	                                                     "#オブジェクトHP加算",
	                                                     "#オブジェクト感情変化設定",
	                                                     "#オブジェクトからの指定相対位置取得",
	                                                     "#オブジェクトから指定位置への距離取得",
	                                                     "#オブジェクトのフェードスタート",
	                                                     "#ユニークキャラIDからオブジェクトID取得",
	                                                     "#オブジェクトの注目オブジェクト設定",
	                                                     "#オブジェクトの注目点設定",
	                                                     "#シナリオビットフラグ設定",
	                                                     "#シナリオビットフラグ取得",
	                                                     "#シナリオカウンターフラグ設定",
	                                                     "#シナリオカウンターフラグ取得",
	                                                     "#座標定義から座標の取得",
	                                                     "#座標定義から座標と向きの取得",
	                                                     "#座標定義で条件に該当するIDをランダム取得",
	                                                     "#座標定義で条件に該当するIDをランダム取得_占有以外",
	                                                     "#座標定義で条件に該当するIDをランダム取得_占有以外(占有フラグオン)",
	                                                     "#座標定義で条件に該当するIDを先頭から取得",
	                                                     "#座標定義で条件に該当するIDを先頭から取得_占有以外",
	                                                     "#座標定義で条件に該当するIDを先頭から取得_占有以外(占有フラグオン)",
	                                                     "#座標の占有を解除",
	                                                     "#座標を占有しているキャラＩＤ取得",
	                                                     "#座標定義の占有",
	                                                     "#座標定義に関連付けられた配置IDを取得",
	                                                     "#座標定義で占有中の次の座標を取得",
	                                                     "#自宅の地区IDを取得",
	                                                     "#効果音再生",
	                                                     "#３Ｄ効果音再生",
	                                                     "#ＢＧＭ再生",
	                                                     "#特殊効果音再生",
	                                                     "#音再生チェック",
	                                                     "#鼻歌再生",
	                                                     "#鼻歌再生(割り当てられたハナウタ自動取得)",
	                                                     "#鼻歌再生ができるか判定",
	                                                     "#鼻歌停止",
	                                                     "#ヘンテコボイス再生",
	                                                     "#ヘンテコボイス停止",
	                                                     "#時間取得",
	                                                     "#行動シーケンスをリセット",
	                                                     "#シグナル発信者のID取得",
	                                                     "#子供がいるかどうかをチェック",
	                                                     "#王様に対する人気度の取得",
	                                                     "#まつり参加状態をクリア",
	                                                     "#職場班分けされた座標定義IDの取得",
	                                                     "#デート待ち合わせの座標行IDの取得",
	                                                     "#ハナウタ開始地点の座標行ＩＤの取得",
	                                                     "#座標定義に絡んだアイテムの制御",
	                                                     "#座標定義に絡んだアイテムの発生位置制御",
	                                                     "#座標定義に絡んだエフェクトの制御",
	                                                     "#座標定義に絡んだエフェクトの状態チェック",
	                                                     "#座標定義に絡んだオブジェクトの自然発生スイッチ",
	                                                     "#フェイシャルアニメ",
	                                                     "#NPC追従開始",
	                                                     "#NPC追従終了",
	                                                     "#NPC追従モーション設定",
	                                                     "#NPC追従移動速度同期設定",
	                                                     "#NPC追従モーション同期設定",
	                                                     "#自分を追従するNPCの追従開始",
	                                                     "#自分を追従するNPCの追従終了",
	                                                     "#自分を追従するNPCの追従モーション設定",
	                                                     "#自分を追従するNPCの移動速度同期設定",
	                                                     "#自分を追従するNPCのモーション同期設定",
	                                                     "#自分を追従しているNPCのID取得",
	                                                     "#狩猫のオブジェクトIDを取得",
	                                                     "#オブジェクトの後方への位置をランダム取得",
	                                                     "#指定オブジェクトの後方への位置をランダム取得",
	                                                     "#オブジェクトの前方への位置をランダム取得",
	                                                     "#指定オブジェクトの前方への位置をランダム取得",
	                                                     "#自分がリーダーの位置にいるか",
	                                                     "#指定オブジェクトの老人状態チェック",
	                                                     "#指定オブジェクトの性別チェック",
	                                                     "#座標定義による整列のリーダーとして整列を作成可能かチェック",
	                                                     "#座標定義による整列の作成",
	                                                     "#指定個性のみで座標定義による整列の作成",
	                                                     "#座標定義による整列の解散",
	                                                     "#座標定義による整列で追従する対象IDを取得",
	                                                     "#座標定義による整列で自分に追従している対象IDを取得",
	                                                     "#座標定義による整列のリーダーIDを取得",
	                                                     "#整列中のNPCのIDを先頭から何番目か指定して取得",
	                                                     "#占有している座標定義の行番号のみ取得",
	                                                     "#占有している座標定義のIDを取得",
	                                                     "#指定オブジェクトとモーション同期",
	                                                     "#座標定義フラグチェック",
	                                                     "#指定オブジェクトのスペキュラ有効設定",
	                                                     "#地面コリジョンチェック",
	                                                     "#内観滞在設定",
	                                                     "#弓矢の発射",
	                                                     "#建物の中にいる任意のNPCのオブジェクトID取得",
	                                                     "#指定オブジェクトのデート相手を取得",
	                                                     "#NPCデート待ち合わせの座標行IDの取得",
	                                                     "#指定オブジェクトのワーク変数へ値設定_整数",
	                                                     "#指定オブジェクトのワーク変数から値取得_整数",
	                                                     "#指定オブジェクトの専用ワーク変数へ値設定_整数",
	                                                     "#指定オブジェクトの専用ワーク変数から値取得_整数",
	                                                     "#指定オブジェクトの影描画設定",
	                                                     "ONE_ACTION_TERM",
	                                                     "指定フレームまで待機",
	                                                     "指定時間まで待機",
	                                                     "指定時間の間待機",
	                                                     "モーション終了待ち",
	                                                     "モーション指定フレームまで待機",
	                                                     "モーション終了まで指定オブジェクトの方を向き続ける",
	                                                     "指定回数モーション再生",
	                                                     "指定位置まで移動",
	                                                     "指定方向を向く",
	                                                     "指定オブジェクトの方を向く",
	                                                     "指定座標の方を向く",
	                                                     "職場へ移動",
	                                                     "自宅へ移動",
	                                                     "自宅へ入る",
	                                                     "自宅から出る",
	                                                     "フィールドから内観へ",
	                                                     "内観からフィールドへ",
	                                                     "オブジェクトフィールドから内観へ",
	                                                     "オブジェクト内観からフィールドへ",
	                                                     "ボタンが押されるまで待機",
	                                                     "会話位置まで移動",
	                                                     "オブジェクトモーション終了待ち",
	                                                     "オブジェクトモーション終了まで指定オブジェクトを向く",
	                                                     "オブジェクト指定回数モーション再生",
	                                                     "オブジェクト指定方向を向く",
	                                                     "オブジェクト指定オブジェクトの方を向く",
	                                                     "オブジェクト指定座標の方を向く",
	                                                     "オブジェクト指定位置まで移動",
	                                                     "指定分まで待機　",
	                                                     "后の部屋に入る",
	                                                     "后の部屋から出る",
	                                                     "弓矢刺さり待ち"
	                                                     };
	//External is 0x12, OR 18. 19th in list
	private static String[] instrutionTypes = new String[] {"Label","Load","Address","Push","Pop",
															"Assign","Add","Subtraction","Multiply","Divide",
															"Modulus","Invert","Compare","JMP","CJMP",
															"CALL","RET","PRNT","EXT","HALT","SPND" };
	ArrayList<String> Instructions = new ArrayList<String>();
	ArrayList<vmInstruction> code = new ArrayList<vmInstruction>();
	ArrayList<String> Strings = new ArrayList<String>();
	ByteBuffer data = null;
	int size = -1;
	public VMCConverter(byte[] data1)
	{
		//ArrayList<String> Instructions = new ArrayList<String>();
		//this.data = data1;
		
		data = ByteBuffer.wrap(data1);
		data.position(12);
		size = data.getInt(4);
		code = new ArrayList<vmInstruction>();
		vmInstruction lastInstruction = null;
		while(data.position() < data.limit() && (lastInstruction == null || !lastInstruction.equals("Halt")))
		{
			lastInstruction = vmInstruction.createInstruction(data);
			code.add(lastInstruction);
		}
		Strings = extractStrings();
	}
	public VMCConverter(List<String> Lines) 
	{
		if(Lines.get(0).indexOf(version)==-1) throw new IllegalArgumentException("Wrong Version of File. Current Version is: " + version);
		int StringStart = -1;
		for(int i = 0; i < Lines.size(); i++)
		{
			String line = Lines.get(i);
			if(line.indexOf("Event Code")!=-1) size = bFM.Utils.strToInt(line);
			else code.add(vmInstruction.createInstruction(line));
			StringStart = i;
			if(line.indexOf("Strings:")!=-1) break;
			
		}
		for(int i = StringStart; i < Lines.size(); i++)
		{
			String line = Lines.get(i);
			if(line.indexOf("Strings:")!=-1) {}
			else Strings.add(line);
		}
	}
	public String toString()
	{
		String ret = version + "\n";
		ret += "Event Code " + size + "\n";
		for(vmInstruction i : code)
		{
			ret += i;
		}
		ret += "Strings: ";
		for(String i : Strings)
		{
			ret += '\n' + i;
		}
		return ret;
	}
	public byte[] toBytes()
	{
		byte[] ret = null;
		ret = bFM.Utils.mergeArrays(ret, "VMC ".getBytes());
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(size, 4));
		ret = bFM.Utils.mergeArrays(ret, new byte[4]);
		for(vmInstruction i : code)
		{
			ret = bFM.Utils.mergeArrays(ret, i.toBytes());
		}
		for(String i : Strings)
		{
			i = i.replace("\\r", "\r");
			i = i.replace("\\n", "\n");
			i = i.replace("\\t", "\t");
			ret = bFM.Utils.mergeArrays(ret, i.getBytes(Charset.forName("SHIFT-JIS")));
			ret = bFM.Utils.mergeArrays(ret, (byte)0);
			if(ret.length%4!=0)
			{
				ret = bFM.Utils.mergeArrays(ret, new byte[4-ret.length%4]);
			}
		}
		return ret;
	}
	private ArrayList<String> extractStrings() 
	{
		//Skip filler 0's
		byte b = data.get(data.position());
		int zeroCount = 0;
		while(b == 0)
		{
			b = data.get();
			zeroCount++;
		}
		if(zeroCount!=0) System.out.println("ZeroCount = " + zeroCount);
		ArrayList<String> Strings = new ArrayList<String>();
		String temp = "";
		int startIndex = data.position();
		int endIndex = startIndex;
		for(int i = data.position(); i<data.limit(); i++)
		{
			if(data.get(i)==0x00)
			{
				endIndex = i-1;
				byte[] stringData = new byte[endIndex-startIndex+1];
				data.get(startIndex, stringData, 0, endIndex-startIndex+1);
				temp = new String(stringData, Charset.forName("SHIFT-JIS"));
				temp = temp.replace("\r", "\\r");
				temp = temp.replace("\n", "\\n");
				temp = temp.replace("\t", "\\t");
				Strings.add(temp);
				i = ((int)(i/4)+1)*4-1;
				startIndex = i+1;
				temp = "";
			}
		}
		return Strings;
	}
}
