package VMC;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class VMCConverter 
{
	byte[] data;
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
	private static int[] nextInt = new int[] {3,4,5,6,7,8,9,10,17,18,21};
	private static int[] nextFloat = new int[] {11,12,13,14,15,16,22};
	public VMCConverter(byte[] data)
	{
		ArrayList<String> Instructions = new ArrayList<String>();
		this.data = data;
	}
	private static boolean contains(int[] arr, int instruction)
	{
		for(int i = 0; i < arr.length; i++)
		{
			if(arr[i]==instruction)return true;
		}
		return false;
	}
	public String toString()
	{
		int instructionsCount = ByteBuffer.wrap(data).getInt(4);
		
		int finalPos = 0;
		for(int position = 8; position<data.length; position+=4)
		{
			int instructionType = ByteBuffer.wrap(data).getInt(position);
			if(instructionType>=instrutionTypes.length ||instructionType<0)
			{
				Instructions.add("Error");
				System.out.println("Error " + instructionType);
			}
			else if(instructionType==0x0)//Label
			{
				//int number1 = ByteBuffer.wrap(data).getInt(position + 4);
				//int number2 = ByteBuffer.wrap(data).getInt(position + 8);
				//int number3 = ByteBuffer.wrap(data).getInt(position + 12);
				String line = instrutionTypes[instructionType];
				//line += " " + number1;
				//line += " " + number2;
				//line += " " + number3;
				System.out.println(line);
				Instructions.add(line);
				//position+=12;
			}
			else if(instructionType==0x1)//Load
			{
				int numberType = -1;
				String line = instrutionTypes[instructionType];
				//numberType = ByteBuffer.wrap(data).getInt(position+8);
				if(numberType==1)
				{
					line += " " + ByteBuffer.wrap(data).getInt(position+4);
				}
				else if(numberType==3)
				{
					line += " " + ByteBuffer.wrap(data).getFloat(position+4);
				}
				else
				{
					line += " " + ByteBuffer.wrap(data).getInt(position+4);
					//line += " " + ByteBuffer.wrap(data).getInt(position+8);
				}
				System.out.println(line);
				Instructions.add(line);
				position += 4;
			}
			else if(instructionType==0x3)//Push
			{
				String line = instrutionTypes[instructionType];
				position += 4;
				int numberType = ByteBuffer.wrap(data).getInt(position+4);
				if(numberType==0)
				{
					line += " " + ByteBuffer.wrap(data).getInt(position);
				}
				else if(numberType==1)
				{
					line += " " + ByteBuffer.wrap(data).getFloat(position);
				}
				else
				{
					line += " " + ByteBuffer.wrap(data).getInt(position);
					line += " " + ByteBuffer.wrap(data).getInt(position+4);
				}
				position += 4;
				
				System.out.println(line);
				Instructions.add(line);
			}
			else if(instructionType==13)//Jump
			{
				
				position += 4;
				String line = instrutionTypes[instructionType] + " " + ByteBuffer.wrap(data).getInt(position);
				System.out.println(line);
				Instructions.add(line);
			}
			else if(instructionType==14)//Compare Jump
			{
				
				position += 4;
				String line = instrutionTypes[instructionType] + " " + ByteBuffer.wrap(data).getInt(position);
				System.out.println(line);
				Instructions.add(line);
			}
			else if(instructionType==15)//Call
			{
				
				position += 4;
				String line = instrutionTypes[instructionType] + " " + ByteBuffer.wrap(data).getInt(position);
				System.out.println(line);
				Instructions.add(line);
			}
			else if(instructionType==0x12)//External; nth String is the function ref I think?
			{
				
				position += 4;
				String line = instrutionTypes[instructionType] + " " + ByteBuffer.wrap(data).getInt(position);
				System.out.println(line);
				Instructions.add(line);
			}
			else if(instructionType==0x13)//Halt; end of code
			{
				String line = instrutionTypes[instructionType];
				System.out.println(line);
				Instructions.add(line);
				finalPos = position+4;
				break;
			}
			else
			{
				System.out.println(instrutionTypes[instructionType]);
				Instructions.add(instrutionTypes[instructionType]);
			}
			
			Instructions.set(Instructions.size()-1, Instructions.get(Instructions.size()-1)+"\n");
			
			finalPos = position+4;
		}
		System.out.println(Instructions.size()+"   "+instructionsCount);
		System.out.println(extractStrings(data, finalPos));
		String ret = "";
		for(int i = 0; i<Instructions.size();i++)
		{
			ret+= Instructions.get(i);
		}
		return ret;
	}
	private int Load(int position, int num1)
	{
		int LoadType;
		int num3;//is LoadType
		int num4;
		//removed
		int num2;
		int num5;
		int num6;
		int num7;
		int num8;
		int InstructionType;
		
		
		
		
		
		LoadType = ByteBuffer.wrap(data).getInt(position + 8);
		InstructionType = ByteBuffer.wrap(data).getInt(position + 4);
		
		if(LoadType != 3)
		{
			if(LoadType < 3)
			{
				if(LoadType == 1)
				{
					//num2 = ByteBuffer.wrap(data).getInt(num1+12) + InstructionType*8;
					//InstructionType = ByteBuffer.wrap(data).getInt(num2);
					//LoadType = ByteBuffer.wrap(data).getInt(num2+4);
					if(ByteBuffer.wrap(data).getInt(num1+4)<= ByteBuffer.wrap(data).getInt(num1+8))
					{
						//num6 = InstructionType;
						//Out of Stack Error
						//Return
					}
					//num2 = ByteBuffer.wrap(data).getInt(num1+8);
					//Increase 8 ahead of num1 by 2, = num2 +2
					//Execute Code??
				}
				else if(0 < LoadType)
				{
					
				}
			}
			else if(LoadType<5)
			{
				
			}
		}
		System.out.println(instrutionTypes[1]);
		Instructions.add(instrutionTypes[1]);
		//System.out.println(line);
		//Instructions.add(line);
		position+=12;
		return position;
	}
	private static ArrayList<String> extractStrings(byte[] data, int startPos)
	{
		ArrayList<String> Strings = new ArrayList<String>();
		String temp = "";
		for(int i = startPos; i<data.length; i++)
		{
			if(data[i]==0x00)
			{
				try {
					Strings.add(new String(temp.getBytes(), "SHIFT-JIS"));
				} catch (UnsupportedEncodingException e) 
				{
					e.printStackTrace();
				}
				i = ((int)(i/4)+1)*4-1;
				temp = "";
			}
			else if(data[i]==0x0d)
			{
				temp+="\\r";
			}
			else if(data[i]==0x0a)
			{
				temp+="\\n";
			}
			else if(data[i]==0x09)
			{
				temp+="\\t";
			}
			else 
			{
				temp+=(char)data[i];
			}
		}
		return Strings;
	}
}
