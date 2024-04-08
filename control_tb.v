`timescale 1ns / 1ps

/*
 *  Project 3
 *  Module  : control_tb
 *  Names   : Joseph Blethen, Alex Karapetkov
 *  Date    : 04-07-2024
 *  Note    : A module that tests the controller for this CPU.
 *  This work complies with the JMU honor code.
 */
module control_tb;
reg [6:0] I;
wire ALUSrc, MemToReg, RegWrite, MemRead, MemWrite, Branch, ALUOp1, ALUOp0;
localparam step = 10;
control ctrl(I, ALUSrc, MemToReg, RegWrite, MemRead, MemWrite, Branch, ALUOp1, ALUOp0);
initial begin
  $display("Begin control module test");
  $monitor("t=%2d I=%6b O=%b%b%b%b%b%b%b%b", $time, I, ALUSrc, MemToReg, RegWrite, MemRead, MemWrite, Branch, ALUOp1, ALUOp0);
  I = 6'b0110011; #step; // R-format test
  I = 6'b0000011; #step; // lw test
  I = 6'b0100011; #step; // sw test
  I = 6'b1100011; #step; // beq test
  I = 6'b0010011; #step; // addi/andi test
  $finish();
end
endmodule
